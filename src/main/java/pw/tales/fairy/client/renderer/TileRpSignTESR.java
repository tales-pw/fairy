package pw.tales.fairy.client.renderer;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import pw.tales.fairy.tile.TileRpSign;

import java.util.List;

@SuppressWarnings("deprecation")
public class TileRpSignTESR
        extends TileEntitySpecialRenderer<TileRpSign> {

    private void applyRotation(TileRpSign te) {
        // bypassing get state from BlockState for purpose of speed
        Direction facing = Direction.byHorizontalIndex(te.getBlockMetadata());

        float angle;
        switch (facing) {
            case EAST:
                angle = 90.0F;
                break;
            case WEST:
                angle = 90.0F;
                break;
            default:
                angle = 0.0f;
        }

        GlStateManager.rotate(angle, 0.0F, 1.0F, 0.0F);
    }

    private void renderText(ITextComponent[] signText, int lineBeingEdited) {
        FontRenderer fontrenderer = this.getFontRenderer();

        for (int j = 0; j < signText.length; ++j) {
            if (signText[j] != null) {
                ITextComponent itextcomponent = signText[j];
                List<ITextComponent> list = GuiUtilRenderComponents
                        .splitText(itextcomponent, 90, fontrenderer, false, true);

                String s = !list.isEmpty() ? list.get(0).getFormattedText() : "";

                if (j == lineBeingEdited)
                    s = "> " + s + " <";

                fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2,
                        j * 10 - signText.length * 5, DyeColor.WHITE.getColorValue());
            }
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void render(TileRpSign te, double x, double y, double z, float partialTicks,
                       int destroyStage, float alpha) {

        // TODO: Get rid of this stupid hack and move everything to separate gui
        boolean isGUI = partialTicks == 0.0f;

        GlStateManager.pushMatrix();

        GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        if (!isGUI)
            this.applyRotation(te);

        GlStateManager.scale(0.010416667F, -0.010416667F, 0.010416667F);
        GlStateManager.depthMask(false);

        this.renderText(te.signText, te.lineBeingEdited);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.renderText(te.signText, te.lineBeingEdited);

        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }
}
