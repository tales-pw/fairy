package pw.tales.fairy.featured.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureFlag extends Feature {
    private final String name;
    private final PropertyBool property;
    private final List<IProperty> properties = new ArrayList<>();

    public FeatureFlag(String name) {
        this.name = name;
        this.property = PropertyBool.create(name);
        this.properties.add(this.property);
    }

    @Override
    public IBlockState getActualState(IBlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        IFlagHandler flagHandler = (IFlagHandler) block;
        return super.getActualState(state, block, world, pos)
                .withProperty(property, flagHandler.handleFlag(this.name, state, block, world, pos));
    }

    @Override
    public List<IProperty> getProperties() {
        return this.properties;
    }

    public PropertyBool getProperty() {
        return this.property;
    }

    public String getName() {
        return name;
    }

    public interface IFlagHandler {
        boolean handleFlag(String name, IBlockState state, Block block, IBlockAccess world,
                           BlockPos pos);
    }
}
