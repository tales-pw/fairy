package pw.tales.fairy.client.renderer;

import net.minecraft.client.renderer.GlStateManager;
import pw.tales.fairy.featured.features.Feature360Rotation;
import pw.tales.fairy.tile.TileSmallCube;

@SuppressWarnings("deprecation") public class TileSmallCubeRenderer
    extends TileBlockModelRenderer<TileSmallCube> {

    @Override public void setUp(TileSmallCube tile, float partialTicks) {
        this.profile = GlStateManager.Profile.PLAYER_SKIN;

        this.rotationY = -22.5F * tile.getWorld().getBlockState(tile.getPos())
            .getValue(Feature360Rotation.ROTATION);
    }
}
