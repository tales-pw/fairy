package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureHRotation extends Feature {
    public static final Feature DEFAULT = new FeatureHRotation();

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    private static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.add(FACING);
    }

    @Override
    public BlockState updateDefaultState(BlockState state) {
        return super.updateDefaultState(state).setValue(FACING, Direction.NORTH);
    }

    @Override
    public BlockState onPlacement(BlockState state, BlockItemUseContext itemUseContext) {
        return state.setValue(FACING, itemUseContext.getHorizontalDirection());
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }

}
