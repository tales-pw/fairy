package pw.tales.fairy.client.renderer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.List;

public abstract class TileBlockModelRenderer<T extends TileEntity>
    extends TileEntitySpecialRenderer<T> {
    protected float rotationX = 0;
    protected float rotationY = 0;
    protected float rotationZ = 0;

    protected float shiftX = 0;
    protected float shiftY = 0;
    protected float shiftZ = 0;

    protected float scale = 1;

    protected GlStateManager.Profile profile = GlStateManager.Profile.DEFAULT;

    private List<BakedQuad> quads;

    public static void renderModelTESRFast(List<BakedQuad> quads, BufferBuilder renderer,
        World world, BlockPos pos) {
        int brightness = world.getCombinedLight(pos, 0);
        int l1 = (brightness >> 0x10) & 0xFFFF;
        int l2 = brightness & 0xFFFF;
        for (BakedQuad quad : quads) {
            int[] vData = quad.getVertexData();
            VertexFormat format = quad.getFormat();
            int size = format.getIntegerSize();
            int uv = format.getUvOffsetById(0) / 4;
            for (int i = 0; i < 4; ++i) {
                renderer.pos(Float.intBitsToFloat(vData[size * i]),
                    Float.intBitsToFloat(vData[size * i + 1]),
                    Float.intBitsToFloat(vData[size * i + 2])).color(255, 255, 255, 255)
                    .tex(Float.intBitsToFloat(vData[size * i + uv]),
                        Float.intBitsToFloat(vData[size * i + uv + 1])).lightmap(l1, l2)
                    .endVertex();
            }

        }
    }

    abstract public void setUp(T tile, float partialTicks);

    @Override public void render(T tile, double x, double y, double z, float partialTicks,
        int destroyStage, float alpha) {
        if (!tile.getWorld().isBlockLoaded(tile.getPos(), false))
            return;

        if (quads == null) {
            final BlockRendererDispatcher blockRenderer =
                Minecraft.getMinecraft().getBlockRendererDispatcher();
            IBlockState state = tile.getBlockType().getDefaultState();
            quads = blockRenderer.getModelForState(state).getQuads(state, null, 0);
        }

        this.setUp(tile, partialTicks);

        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.pushMatrix();

        GlStateManager.translate(x + .5, y + .5, z + .5);

        profile.apply();
        GlStateManager.disableCull();

        GlStateManager.rotate(rotationX, 1, 0, 0);
        GlStateManager.rotate(rotationY, 0, 1, 0);
        GlStateManager.rotate(rotationZ, 0, 0, 1);

        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

        GlStateManager.scale(scale, scale, scale);

        GlStateManager.translate(shiftX - 0.5, shiftY - 0.5, shiftZ - 0.5);

        renderModelTESRFast(quads, buffer, tile.getWorld(), tile.getPos());

        buffer.setTranslation(0, 0, 0);
        tessellator.draw();
        GlStateManager.popMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
    }
}
