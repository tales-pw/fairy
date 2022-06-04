package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.Property;
import net.minecraft.state.PropertyDirection;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureRotation extends Feature {
    public static final Feature DEFAULT = new FeatureRotation();

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    private static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.add(FACING);
    }

    @Override
    public BlockState getDefaultState(BlockState state) {
        return super.getDefaultState(state).withProperty(FACING, Direction.NORTH);
    }

    @Override
    public BlockState onPlacement(BlockState state, World worldIn, BlockPos pos,
                                   Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
        return state.withProperty(FACING, facing);
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }


    public interface IRotationAccess {
        Direction getFacing(BlockState state);
    }
}
