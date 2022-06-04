package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.state.Property;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    public BlockState getDefaultState(BlockState state) {
        return super.getDefaultState(state).withProperty(COLOR, DyeColor.WHITE);
    }

    @Override
    public BlockState onPlacement(BlockState state, World worldIn, BlockPos pos,
                                   Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
        return state.withProperty(COLOR, DyeColor.byMetadata(meta));
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
