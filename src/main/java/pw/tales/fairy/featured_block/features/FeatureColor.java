package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureColor extends Feature {
    public static final Feature DEFAULT = new FeatureColor();

    public static final EnumProperty<DyeColor> COLOR =
            EnumProperty.create("color", DyeColor.class);

    private static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.add(COLOR);
    }

    @Override
    public BlockState updateDefaultState(BlockState state) {
        return super.updateDefaultState(state).setValue(COLOR, DyeColor.WHITE);
    }

    @Override
    public BlockState onPlacement(BlockState state, BlockItemUseContext itemUseContext) {
        ItemStack itemStack = itemUseContext.getItemInHand();

        DyeColor color = DyeColor.getColor(itemStack);
        if (color == null) {
            color = DyeColor.WHITE;
        }

        return state.setValue(COLOR, color);
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
