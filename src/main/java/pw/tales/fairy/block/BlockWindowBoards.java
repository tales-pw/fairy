package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured.features.FeatureHRotation;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation") @MethodsReturnNonnullByDefault public class BlockWindowBoards
    extends BlockSimple {
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(-0.3D, 0.0D, 0.0D, 1.3D, 1.0D, 0.1D);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(-0.3D, 0.0D, 0.9D, 1.3D, 1.0D, 1.0D);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, -0.3D, 0.1D, 1.0D, 1.3D);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.9D, 0.0D, -0.3D, 1.0D, 1.0D, 1.3D);

    public BlockWindowBoards() {
        super(Material.WOOD);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(FeatureHRotation.FACING)) {
            case NORTH:
                return NORTH_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case EAST:
                return EAST_AABB;
            case WEST:
                return WEST_AABB;
        }
        return WEST_AABB;
    }
}
