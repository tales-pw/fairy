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
    public BlockState updateDefaultState(BlockState state) {
        return super.updateDefaultState(state).setValue(N, false).setValue(E, false)
                .setValue(S, false).setValue(W, false);
    }

    @Override
    public BlockState getActualState(BlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        if (!(block instanceof IConnectible))
            return state;

        return state.setValue(N, canConnectTo(world, state, pos, Direction.NORTH))
                .setValue(E, canConnectTo(world, state, pos, Direction.EAST))
                .setValue(S, canConnectTo(world, state, pos, Direction.SOUTH))
                .setValue(W, canConnectTo(world, state, pos, Direction.WEST));

    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
