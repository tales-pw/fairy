package pw.tales.fairy.featured_block.features;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;
import java.util.List;

public class Feature360Rotation extends Feature {
    public static final Feature360Rotation DEFAULT = new Feature360Rotation();
    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 15);

    private final static List<Property<?>> properties = Collections.singletonList(Feature360Rotation.ROTATION);

    @Override
    public BlockState onPlacement(BlockState state, BlockItemUseContext itemUseContext) {
        float placerRotation = itemUseContext.getRotation();
        float rotation = MathHelper.floor((placerRotation * 16.0F / 360.0F) + 0.5D) & 15;
        return state.setValue(ROTATION, (int) rotation);
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
