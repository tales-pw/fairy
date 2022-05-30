package pw.tales.fairy.featured.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import pw.tales.fairy.featured.Pair;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureStoreFlag extends Feature {
    protected final PropertyBool property;
    protected final List<IProperty> properties = new ArrayList<>();

    public FeatureStoreFlag(String name) {
        this(PropertyBool.create(name));
    }

    public FeatureStoreFlag(PropertyBool property) {
        this.property = property;
        this.properties.add(this.property);
    }

    @Override
    public IBlockState getDefaultState(IBlockState state) {
        return state.withProperty(property, false);
    }

    @Override
    public int putToMeta(int oldMeta, IBlockState state) {
        return oldMeta << 1 | (state.getValue(this.property) ? 1 : 0);
    }

    @Override
    public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        return new Pair<>(oldMeta >> 1, state.withProperty(this.property, (oldMeta & 1) == 1));
    }

    public PropertyBool getProperty() {
        return property;
    }

    @Override
    public List<IProperty> getProperties() {
        return this.properties;
    }
}
