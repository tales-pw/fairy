package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureRotation extends Feature {
    public static final Feature DEFAULT = new FeatureRotation();

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    private static final List<IProperty> properties = new ArrayList<>();

    static {
        properties.add(FACING);
    }

    @Override
    public IBlockState getDefaultState(IBlockState state) {
        return super.getDefaultState(state).withProperty(FACING, Direction.NORTH);
    }

    @Override
    public IBlockState onPlacement(IBlockState state, World worldIn, BlockPos pos,
                                   Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
        return state.withProperty(FACING, facing);
    }

    @Override
    public int putToMeta(int oldMeta, IBlockState state) {
        return oldMeta << 3 | state.getValue(FACING).getIndex();
    }

    @Override
    public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        return new Pair<>(oldMeta >> 3,
                state.withProperty(FACING, Direction.byIndex(oldMeta & 7)));
    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }


    public interface IRotationAccess {
        Direction getFacing(IBlockState state);
    }
}
