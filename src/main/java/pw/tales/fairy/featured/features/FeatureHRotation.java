package pw.tales.fairy.featured.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.featured.Pair;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault public class FeatureHRotation extends Feature {
    public static final Feature DEFAULT = new FeatureHRotation();

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    private static final List<IProperty> properties = new ArrayList<>();

    static {
        properties.add(FACING);
    }

    public static void main(String[] args) {
        BlockStateContainer container = new BlockStateContainer(null, FACING);
        IBlockState defaultState = DEFAULT.getDefaultState(container.getBaseState());

        int south_meta = DEFAULT.putToMeta(0, defaultState.withProperty(FACING, EnumFacing.SOUTH));
        int west_meta = DEFAULT.putToMeta(0, defaultState.withProperty(FACING, EnumFacing.WEST));
        int north_meta = DEFAULT.putToMeta(0, defaultState.withProperty(FACING, EnumFacing.NORTH));
        int east_meta = DEFAULT.putToMeta(0, defaultState.withProperty(FACING, EnumFacing.EAST));

        System.out.println("South: " + south_meta);
        System.out.println("West: " + west_meta);
        System.out.println("North: " + north_meta);
        System.out.println("East: " + east_meta);

        System.out.println("South: " + DEFAULT
            .getFromMeta(south_meta, defaultState.withProperty(FACING, EnumFacing.SOUTH)));
        System.out.println("West: " + DEFAULT
            .getFromMeta(west_meta, defaultState.withProperty(FACING, EnumFacing.WEST)));
        System.out.println("North: " + DEFAULT
            .getFromMeta(north_meta, defaultState.withProperty(FACING, EnumFacing.NORTH)));
        System.out.println("East: " + DEFAULT
            .getFromMeta(east_meta, defaultState.withProperty(FACING, EnumFacing.EAST)));
    }

    @Override public IBlockState getDefaultState(IBlockState state) {
        return super.getDefaultState(state).withProperty(FACING, EnumFacing.NORTH);
    }

    @Override public IBlockState onPlacement(IBlockState state, World worldIn, BlockPos pos,
        EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return state.withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override public int putToMeta(int oldMeta, IBlockState state) {
        return oldMeta << 2 | state.getValue(FACING).getHorizontalIndex();
    }

    @Override public List<IProperty> getProperties() {
        return properties;
    }

    @Override public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        return new Pair<>(oldMeta >> 2,
            state.withProperty(FACING, EnumFacing.byHorizontalIndex(oldMeta & 3)));
    }
}
