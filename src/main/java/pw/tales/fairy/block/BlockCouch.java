package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured_block.features.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockCouch
        extends BlockFairy implements FeatureConnection.IConnectible, FeatureRotation.IRotationAccess {

    protected static final AxisAlignedBB AABB_BOTTOM_HALF =
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.45D, 1.0D);

    public BlockCouch() {
        super(Material.WOOD);
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos,
                                            Direction face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn,
                                                 BlockPos pos) {
        return AABB_BOTTOM_HALF;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();
        features.add(FeatureHRotation.DEFAULT);
        features.add(FeatureConnectionDirectional.DEFAULT);
        return features;
    }

    @Override
    public boolean canConnect(Block block2) {
        return block2 instanceof BlockCouch;
    }

    public Direction getFacing(IBlockState state) {
        return state.getValue(FeatureHRotation.FACING);
    }
}
