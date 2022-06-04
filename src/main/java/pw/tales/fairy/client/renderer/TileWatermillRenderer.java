package pw.tales.fairy.client.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;
import pw.tales.fairy.featured_block.features.FeatureHRotation;
import pw.tales.fairy.tile.TileWatermill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(Side.CLIENT)
public class TileWatermillRenderer
        extends TileEntitySpecialRenderer<TileWatermill> {
    private static final float SPEED = 0.003f;
    private static final float SCALE = 10f;
    private static Map<String, List<BakedQuad>> cache = new HashMap<>();
    private static float rotation = 0;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        rotation += SPEED;
        rotation %= 1;
    }

    private List<BakedQuad> getQuads(BlockState state, BlockPos blockPos) {
        String stateString = state.toString();
        List<BakedQuad> quads = cache.getOrDefault(stateString, null);

        if (quads != null)
            return quads;

        BlockRendererDispatcher blockRenderer =
                Minecraft.getMinecraft().getBlockRendererDispatcher();

        state = state.getActualState(getWorld(), blockPos);

        IBakedModel model = blockRenderer.getBlockModelShapes().getModelForState(state);
        quads = model.getQuads(state, null, 0);

        cache.put(stateString, quads);
        return quads;
    }

    @Override
    public void render(TileWatermill tile, double x, double y, double z, float partialTicks,
                       int destroyStage, float alpha) {
        BlockPos blockPos = tile.getPos();
        BlockState state = getWorld().getBlockState(blockPos);

        if (!state.getProperties().containsKey(FeatureHRotation.FACING))
            return;

        Vec3i facing = state.getValue(FeatureHRotation.FACING).getDirectionVec();
        List<BakedQuad> quads = getQuads(state, blockPos);

        float angle = 360 * (rotation + partialTicks * SPEED);

        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.pushMatrix();

        GlStateManager.translate(x + 0.5, y - 2.5D, z + 0.5);
        GlStateManager.rotate(angle, facing.getZ(), 0, -facing.getX());
        GlStateManager.scale(SCALE, SCALE, SCALE);
        GlStateManager.translate(-0.5, 0, -0.5);

        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        TileBlockModelRenderer.renderModelTESRFast(quads, buffer, tile.getWorld(), blockPos);
        tessellator.draw();
        GlStateManager.popMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
    }
}
