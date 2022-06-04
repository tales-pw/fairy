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
public class FeatureConnectionBasic extends FeatureConnection {
    public static final Feature DEFAULT = new FeatureConnectionBasic();

    public static final BooleanProperty N = BooleanProperty.create("north");
    public static final BooleanProperty E = BooleanProperty.create("east");
    public static final BooleanProperty S = BooleanProperty.create("south");
    public static final BooleanProperty W = BooleanProperty.create("west");

    static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.add(N);
        properties.add(E);
        properties.add(S);
        properties.add(W);
    }

    @Override
    public BlockState getDefaultState(BlockState state) {
        return super.getDefaultState(state).withProperty(N, false).withProperty(E, false)
                .withProperty(S, false).withProperty(W, false);
    }

    @Override
    public BlockState getActualState(BlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        if (!(block instanceof IConnectible))
            return state;

        return state.withProperty(N, canConnectTo(world, state, pos, Direction.NORTH))
                .withProperty(E, canConnectTo(world, state, pos, Direction.EAST))
                .withProperty(S, canConnectTo(world, state, pos, Direction.SOUTH))
                .withProperty(W, canConnectTo(world, state, pos, Direction.WEST));

    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
