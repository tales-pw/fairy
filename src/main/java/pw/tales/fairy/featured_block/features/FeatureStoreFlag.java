package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureStoreFlag extends Feature {
    protected final BooleanProperty property;
    protected final List<Property<?>> properties = new ArrayList<>();

    public FeatureStoreFlag(String name) {
        this(BooleanProperty.create(name));
    }

    public FeatureStoreFlag(BooleanProperty property) {
        this.property = property;
        this.properties.add(this.property);
    }

    @Override
    public BlockState updateDefaultState(BlockState state) {
        return state.setValue(property, false);
    }

    public BooleanProperty getProperty() {
        return property;
    }

    @Override
    public List<Property<?>> getProperties() {
        return this.properties;
    }
}
