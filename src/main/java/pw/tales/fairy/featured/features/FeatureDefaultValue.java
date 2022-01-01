package pw.tales.fairy.featured.features;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems") public class FeatureDefaultValue extends Feature {
    private final PropertyBool property;
    private final String name;

    private final List<IProperty> properties = new ArrayList<>();

    public FeatureDefaultValue(String name) {
        this.name = name;
        this.property = PropertyBool.create(name);
        this.properties.add(this.property);
    }

    @Override public IBlockState getDefaultState(IBlockState state) {
        IDefaultValueHandler block = (IDefaultValueHandler) state.getBlock();

        return state.withProperty(property, block.getDefaultValue(this.name));
    }

    @Override public List<IProperty> getProperties() {
        return properties;
    }

    public interface IDefaultValueHandler {
        boolean getDefaultValue(String name);
    }
}
