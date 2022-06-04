package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureConnectionDirectional extends FeatureConnection {
    public static final Feature DEFAULT = new FeatureConnectionDirectional();

    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    public static final BooleanProperty RIGHT = BooleanProperty.create("right");

    static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.add(LEFT);
        properties.add(RIGHT);
    }

    @Override
    public boolean canConnectTo(BlockState state1, BlockState state2) {
        Block block1 = state1.getBlock();
        Block block2 = state2.getBlock();

        if (!(block1 instanceof FeatureRotation.IRotationAccess))
            return false;

        if (!(block2 instanceof FeatureRotation.IRotationAccess))
            return false;

        FeatureRotation.IRotationAccess rot1 = (FeatureRotation.IRotationAccess) block1;
        FeatureRotation.IRotationAccess rot2 = (FeatureRotation.IRotationAccess) block2;

        if (rot1.getFacing(state1) != rot2.getFacing(state2))
            return false;

        return super.canConnectTo(state1, state2);
    }

    @Override
    public BlockState updateDefaultState(BlockState state) {
        return state.setValue(FeatureHRotation.FACING, Direction.NORTH)
                .setValue(LEFT, false).setValue(RIGHT, false);
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Block block,
            IWorld world,
            BlockPos pos
    ) {
        if (!(block instanceof FeatureConnection.IConnectible
                && block instanceof FeatureRotation.IRotationAccess))
            return state;

        FeatureRotation.IRotationAccess rotatable = (FeatureRotation.IRotationAccess) block;
        Direction facing = rotatable.getFacing(state);

        return state
                .setValue(LEFT, this.canConnectTo(world, state, pos, facing.getClockWise()))
                .setValue(RIGHT, this.canConnectTo(world, state, pos, facing.getCounterClockWise()));
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
