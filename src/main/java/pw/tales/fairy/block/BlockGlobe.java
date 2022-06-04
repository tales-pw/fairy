package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.Property<?>;
import net.minecraft.state.IntegerProperty;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureHRotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@MethodsReturnNonnullByDefault
public class BlockGlobe extends BlockFairy {
    protected static final AxisAlignedBB DEFAULT_AABB =
            new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.6D, 0.75D);

    public BlockGlobe() {
        super(Material.WOOD);
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
    public AxisAlignedBB getBoundingBox(BlockState state, IWorld source, BlockPos pos) {
        return DEFAULT_AABB;
    }

    @Override
    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();
        features.add(FeatureHRotation.DEFAULT);
        features.add(GlobeRotation.INSTANCE);
        return features;
    }

    @MethodsReturnNonnullByDefault
    static class GlobeRotation extends Feature {
        private final static GlobeRotation INSTANCE = new GlobeRotation();
        private final static IntegerProperty GLOBE_ROTATION =
                IntegerProperty.create("globe_rotation", 0, 3);

        private final static List<Property<?>> properties = Collections.singletonList(GLOBE_ROTATION);

        @Override
        public ActionResultType onUse(
                World worldIn,
                BlockPos pos,
                BlockState state,
                PlayerEntity playerIn,
                Hand hand,
                BlockRayTraceResult hit
        ) {
            if (worldIn.setBlockAndUpdate(pos, state.cycle(GLOBE_ROTATION))) {
                return ActionResultType.SUCCESS;
            }

            return ActionResultType.FAIL;
        }

        @Override
        public List<Property<?>> getProperties() {
            return properties;
        }

        @Override
        public BlockState updateDefaultState(BlockState state) {
            return state.setValue(GLOBE_ROTATION, 0);
        }
    }
}
