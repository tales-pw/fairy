package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureFlag extends Feature {
    private final String name;
    private final BooleanProperty property;
    private final List<Property<?>> properties = new ArrayList<>();

    public FeatureFlag(String name) {
        this.name = name;
        this.property = BooleanProperty.create(name);
        this.properties.add(this.property);
    }

    @Override
    public BlockState getActualState(BlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        IFlagHandler flagHandler = (IFlagHandler) block;
        return super.getActualState(state, block, world, pos)
                .setValue(property, flagHandler.handleFlag(this.name, state, block, world, pos));
    }

    @Override
    public List<Property<?>> getProperties() {
        return this.properties;
    }

    public BooleanProperty getProperty() {
        return this.property;
    }

    public String getName() {
        return name;
    }

    public interface IFlagHandler {
        boolean handleFlag(String name, BlockState state, Block block, IBlockAccess world,
                           BlockPos pos);
    }
}
