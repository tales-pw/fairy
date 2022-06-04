package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureConnectionFull extends FeatureConnectionBasic {
    public static final Feature DEFAULT = new FeatureConnectionFull();

    public static final BooleanProperty NE = BooleanProperty.create("north_east");
    public static final BooleanProperty SE = BooleanProperty.create("south_east");
    public static final BooleanProperty SW = BooleanProperty.create("south_west");
    public static final BooleanProperty NW = BooleanProperty.create("north_west");

    private static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.addAll(FeatureConnectionBasic.properties);
        properties.add(NE);
        properties.add(SE);
        properties.add(SW);
        properties.add(NW);
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }

    @Override
    public BlockState updateDefaultState(BlockState state) {
        return super.updateDefaultState(state).setValue(NE, false).setValue(SE, false)
                .setValue(SW, false).setValue(NW, false);
    }

    @Override
    public BlockState getActualState(
            BlockState state,
            Block block,
            IBlockAccess world,
            BlockPos pos
    ) {
        BlockState newState = super.getActualState(state, block, world, pos);

        if (!(block instanceof IConnectible))
            return state;

        newState = newState
                .setValue(N,
                        newState.getValue(N) || canConnectTo(world, state, pos, Direction.NORTH, Direction.UP)
                ).setValue(E,
                        newState.getValue(E) || canConnectTo(world, state, pos, Direction.EAST, Direction.UP)
                ).setValue(S,
                        newState.getValue(S) || canConnectTo(world, state, pos, Direction.SOUTH, Direction.UP)
                ).setValue(W,
                        newState.getValue(W) || canConnectTo(world, state, pos, Direction.WEST, Direction.UP)
                );

        if (!(newState.getValue(S) || newState.getValue(E)))
            newState = newState.setValue(SE, canConnectTo(world, state, pos, Direction.SOUTH, Direction.EAST));

        if (!(newState.getValue(S) || newState.getValue(W)))
            newState = newState.setValue(SW, canConnectTo(world, state, pos, Direction.SOUTH, Direction.WEST));

        if (!(newState.getValue(N) || newState.getValue(W)))
            newState = newState.setValue(NW, canConnectTo(world, state, pos, Direction.NORTH, Direction.WEST));

        if (!(newState.getValue(N) || newState.getValue(E)))
            newState = newState.setValue(NE, canConnectTo(world, state, pos, Direction.NORTH, Direction.EAST));

        return newState;
    }
}
