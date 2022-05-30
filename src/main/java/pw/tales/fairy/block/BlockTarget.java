package pw.tales.fairy.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings("deprecation")
public class BlockTarget extends BlockSimple {
    protected static final AxisAlignedBB COLLISION_AABB =
            new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.2D, 0.75D);

    public BlockTarget() {
        super(Material.WOOD);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return COLLISION_AABB;
    }
}
