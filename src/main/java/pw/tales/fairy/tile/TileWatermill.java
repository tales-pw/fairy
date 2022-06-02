package pw.tales.fairy.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;

public class TileWatermill extends TileEntity {
    public TileWatermill(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos().add(-7, -8, -7), getPos().add(7, 5, 7));
    }
}
