package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;
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
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return DEFAULT_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
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
        private final static PropertyInteger GLOBE_ROTATION =
                PropertyInteger.create("globe_rotation", 0, 3);

        private final static List<IProperty> properties = Collections.singletonList(GLOBE_ROTATION);

        @Override
        public int putToMeta(int oldMeta, IBlockState state) {
            return oldMeta << 2 | state.getValue(GLOBE_ROTATION);
        }

        @Override
        public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
            return new Pair<>(oldMeta >> 2, state.withProperty(GLOBE_ROTATION, oldMeta & 3));
        }

        @Override
        public boolean onActivated(World worldIn, BlockPos pos, IBlockState state,
                                   PlayerEntity playerIn, Hand hand, EnumFacing facing, float hitX, float hitY,
                                   float hitZ) {
            return worldIn.setBlockState(pos, state.cycleProperty(GLOBE_ROTATION));
        }

        @Override
        public List<IProperty> getProperties() {
            return properties;
        }

        @Override
        public IBlockState getDefaultState(IBlockState state) {
            return state.withProperty(GLOBE_ROTATION, 0);
        }
    }
}
