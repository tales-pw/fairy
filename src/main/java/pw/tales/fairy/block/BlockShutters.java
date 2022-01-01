package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured.features.Feature;
import pw.tales.fairy.featured.features.FeatureHRotation;
import pw.tales.fairy.featured.features.FeatureSwitch;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockShutters
        extends BlockFairy {
    private static final PropertyBool OPEN_PROPERTY = PropertyBool.create("open");
    private static final FeatureSwitch SWITCH_FEATURE = new FeatureSwitch(OPEN_PROPERTY);

    private AxisAlignedBB SOUTH_CLOSE_AABB = new AxisAlignedBB(0, 0, 0.875, 1, 1, 1);
    private AxisAlignedBB NORTH_CLOSE_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.125);
    private AxisAlignedBB WEST_CLOSE_AABB = new AxisAlignedBB(0, 0, 0, 0.125, 1, 1);
    private AxisAlignedBB EAST_CLOSE_AABB = new AxisAlignedBB(0.875, 0, 0, 1, 1, 1);

    public BlockShutters() {
        super(Material.WOOD);
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing facing = state.getValue(FeatureHRotation.FACING);
        switch (facing) {
            case SOUTH:
                return SOUTH_CLOSE_AABB;
            case NORTH:
                return NORTH_CLOSE_AABB;
            case WEST:
                return WEST_CLOSE_AABB;
            case EAST:
            default:
                return EAST_CLOSE_AABB;
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos,
                                            EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @ParametersAreNonnullByDefault
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn,
                                                 BlockPos pos) {
        if (blockState.getValue(OPEN_PROPERTY)) {
            return NULL_AABB;
        }
        return this.getBoundingBox(blockState, worldIn, pos);
    }

    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();
        features.add(FeatureHRotation.DEFAULT);
        features.add(SWITCH_FEATURE);
        return features;
    }
}
