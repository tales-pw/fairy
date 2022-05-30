package pw.tales.fairy.featured.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureConnectionFull extends FeatureConnectionBasic {
    public static final Feature DEFAULT = new FeatureConnectionFull();

    public static final PropertyBool NE = PropertyBool.create("north_east");
    public static final PropertyBool SE = PropertyBool.create("south_east");
    public static final PropertyBool SW = PropertyBool.create("south_west");
    public static final PropertyBool NW = PropertyBool.create("north_west");

    private static final List<IProperty> properties = new ArrayList<>();

    static {
        properties.addAll(FeatureConnectionBasic.properties);
        properties.add(NE);
        properties.add(SE);
        properties.add(SW);
        properties.add(NW);
    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }

    @Override
    public IBlockState getDefaultState(IBlockState state) {
        return super.getDefaultState(state).withProperty(NE, false).withProperty(SE, false)
                .withProperty(SW, false).withProperty(NW, false);
    }

    @Override
    public IBlockState getActualState(IBlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        IBlockState newState = super.getActualState(state, block, world, pos);

        if (!(block instanceof IConnectible))
            return state;

        newState = newState.withProperty(N,
                        newState.getValue(N) || canConnectTo(world, state, pos, EnumFacing.NORTH,
                                EnumFacing.UP)).withProperty(E,
                        newState.getValue(E) || canConnectTo(world, state, pos, EnumFacing.EAST, EnumFacing.UP))
                .withProperty(S,
                        newState.getValue(S) || canConnectTo(world, state, pos, EnumFacing.SOUTH,
                                EnumFacing.UP)).withProperty(W,
                        newState.getValue(W) || canConnectTo(world, state, pos, EnumFacing.WEST,
                                EnumFacing.UP));

        if (!(newState.getValue(S) || newState.getValue(E)))
            newState = newState.withProperty(SE,
                    canConnectTo(world, state, pos, EnumFacing.SOUTH, EnumFacing.EAST));

        if (!(newState.getValue(S) || newState.getValue(W)))
            newState = newState.withProperty(SW,
                    canConnectTo(world, state, pos, EnumFacing.SOUTH, EnumFacing.WEST));

        if (!(newState.getValue(N) || newState.getValue(W)))
            newState = newState.withProperty(NW,
                    canConnectTo(world, state, pos, EnumFacing.NORTH, EnumFacing.WEST));

        if (!(newState.getValue(N) || newState.getValue(E)))
            newState = newState.withProperty(NE,
                    canConnectTo(world, state, pos, EnumFacing.NORTH, EnumFacing.EAST));

        return newState;
    }
}
