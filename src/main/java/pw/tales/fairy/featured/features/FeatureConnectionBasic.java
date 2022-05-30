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
public class FeatureConnectionBasic extends FeatureConnection {
    public static final Feature DEFAULT = new FeatureConnectionBasic();

    public static final PropertyBool N = PropertyBool.create("north");
    public static final PropertyBool E = PropertyBool.create("east");
    public static final PropertyBool S = PropertyBool.create("south");
    public static final PropertyBool W = PropertyBool.create("west");

    static final List<IProperty> properties = new ArrayList<>();

    static {
        properties.add(N);
        properties.add(E);
        properties.add(S);
        properties.add(W);
    }

    @Override
    public IBlockState getDefaultState(IBlockState state) {
        return super.getDefaultState(state).withProperty(N, false).withProperty(E, false)
                .withProperty(S, false).withProperty(W, false);
    }

    @Override
    public IBlockState getActualState(IBlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        if (!(block instanceof IConnectible))
            return state;

        return state.withProperty(N, canConnectTo(world, state, pos, EnumFacing.NORTH))
                .withProperty(E, canConnectTo(world, state, pos, EnumFacing.EAST))
                .withProperty(S, canConnectTo(world, state, pos, EnumFacing.SOUTH))
                .withProperty(W, canConnectTo(world, state, pos, EnumFacing.WEST));

    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }
}
