package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureHRotation;
import pw.tales.fairy.featured_block.features.FeatureSwitch;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockTaperCandle
        extends BlockFairy {
    private static final BooleanProperty LIT_PROPERTY = BooleanProperty.create("lit");
    private static final FeatureSwitch SWITCH_FEATURE = new FeatureSwitch(LIT_PROPERTY);

    private static final AxisAlignedBB BOUNDING_BOX =
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

    public BlockTaperCandle() {
        super(Material.CLAY);
    }

    @Override
    public boolean isFullBlock(BlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(BlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @ParametersAreNonnullByDefault
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(BlockState blockState, IBlockAccess worldIn,
                                                 BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public int getLightValue(BlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(LIT_PROPERTY))
            return 15;
        return super.getLightValue(state, world, pos);
    }

    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();
        features.add(FeatureHRotation.DEFAULT);
        features.add(SWITCH_FEATURE);
        return features;
    }
}
