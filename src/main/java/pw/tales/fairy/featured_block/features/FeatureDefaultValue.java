package pw.tales.fairy.featured_block.features;

import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class FeatureDefaultValue extends Feature {
    private final BooleanProperty property;
    private final String name;

    private final List<Property<?>> properties = new ArrayList<>();

    public FeatureDefaultValue(String name) {
        this.name = name;
        this.property = BooleanProperty.create(name);
        this.properties.add(this.property);
    }

    @Override
    public BlockState updateDefaultState(BlockState state) {
        IDefaultValueHandler block = (IDefaultValueHandler) state.getBlock();

        return state.setValue(property, block.getDefaultValue(this.name));
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }

    public interface IDefaultValueHandler {
        boolean getDefaultValue(String name);
    }
}
