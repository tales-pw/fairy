package pw.tales.fairy.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileWatermill extends TileEntity {
    @Override public double getMaxRenderDistanceSquared() {
        return Double.POSITIVE_INFINITY;
    }

    @Override public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos().add(-7, -8, -7), getPos().add(7, 5, 7));
    }
}
