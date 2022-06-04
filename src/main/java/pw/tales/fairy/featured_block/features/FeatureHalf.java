package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import pw.tales.fairy.featured_block.Pair;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureHalf extends Feature {
    public static final Feature DEFAULT = new FeatureHalf();

    public static final EnumProperty<SlabType> HALF = EnumProperty.create("half", SlabType.class);

    private static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.add(HALF);
    }

    @Override
    public BlockState updateDefaultState(BlockState state) {
        return super.updateDefaultState(state).setValue(HALF, SlabType.TOP);
    }

    @Override
    public BlockState onPlacement(
            BlockState state, BlockItemUseContext itemUseContext) {
        return facing != Direction.DOWN && (facing == Direction.UP || (double) hitY <= 0.5D) ?
                state.setValue(HALF, SlabType.BOTTOM) :
                state.setValue(HALF, SlabType.TOP);
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
