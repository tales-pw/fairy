package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockStateContainer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.Property;
import net.minecraft.state.PropertyDirection;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureHRotation extends Feature {
    public static final Feature DEFAULT = new FeatureHRotation();

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    private static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.add(FACING);
    }

    public static void main(String[] args) {
        BlockStateContainer container = new BlockStateContainer(null, FACING);
        BlockState defaultState = DEFAULT.getDefaultState(container.getBaseState());

        int south_meta = DEFAULT.putToMeta(0, defaultState.withProperty(FACING, Direction.SOUTH));
        int west_meta = DEFAULT.putToMeta(0, defaultState.withProperty(FACING, Direction.WEST));
        int north_meta = DEFAULT.putToMeta(0, defaultState.withProperty(FACING, Direction.NORTH));
        int east_meta = DEFAULT.putToMeta(0, defaultState.withProperty(FACING, Direction.EAST));

        System.out.println("South: " + south_meta);
        System.out.println("West: " + west_meta);
        System.out.println("North: " + north_meta);
        System.out.println("East: " + east_meta);

        System.out.println("South: " + DEFAULT
                .getFromMeta(south_meta, defaultState.withProperty(FACING, Direction.SOUTH)));
        System.out.println("West: " + DEFAULT
                .getFromMeta(west_meta, defaultState.withProperty(FACING, Direction.WEST)));
        System.out.println("North: " + DEFAULT
                .getFromMeta(north_meta, defaultState.withProperty(FACING, Direction.NORTH)));
        System.out.println("East: " + DEFAULT
                .getFromMeta(east_meta, defaultState.withProperty(FACING, Direction.EAST)));
    }

    @Override
    public BlockState getDefaultState(BlockState state) {
        return super.getDefaultState(state).withProperty(FACING, Direction.NORTH);
    }

    @Override
    public BlockState onPlacement(BlockState state, World worldIn, BlockPos pos,
                                   Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
        return state.withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }

}
