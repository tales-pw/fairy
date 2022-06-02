package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureConnectionDirectional extends FeatureConnection {
    public static final Feature DEFAULT = new FeatureConnectionDirectional();

    public static final PropertyBool LEFT = PropertyBool.create("left");
    public static final PropertyBool RIGHT = PropertyBool.create("right");

    static final List<IProperty> properties = new ArrayList<>();

    static {
        properties.add(LEFT);
        properties.add(RIGHT);
    }

    @Override
    public boolean canConnectTo(IBlockState state1, IBlockState state2) {
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
    public IBlockState getDefaultState(IBlockState state) {
        return state.withProperty(FeatureHRotation.FACING, Direction.NORTH)
                .withProperty(LEFT, false).withProperty(RIGHT, false);
    }

    @Override
    public IBlockState getActualState(IBlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        if (!(block instanceof FeatureConnection.IConnectible
                && block instanceof FeatureRotation.IRotationAccess))
            return state;

        FeatureRotation.IRotationAccess rotatable = (FeatureRotation.IRotationAccess) block;
        Direction facing = rotatable.getFacing(state);

        return state.withProperty(LEFT, this.canConnectTo(world, state, pos, facing.rotateY()))
                .withProperty(RIGHT, this.canConnectTo(world, state, pos, facing.rotateYCCW()));
    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }
}
