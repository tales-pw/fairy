package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.Property;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureHalf extends Feature {
    public static final Feature DEFAULT = new FeatureHalf();

    public static final EnumProperty<BlockSlab.EnumBlockHalf> HALF =
            EnumProperty.<BlockSlab.EnumBlockHalf>create("half", BlockSlab.EnumBlockHalf.class);

    private static final List<Property<?>> properties = new ArrayList<>();

    static {
        properties.add(HALF);
    }

    @Override
    public BlockState getDefaultState(BlockState state) {
        return super.getDefaultState(state).withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
    }

    private int putToMeta(int oldMeta, BlockState state) {
        return oldMeta << 1 | (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? 0 : 1);
    }

    private Pair<Integer, BlockState> getFromMeta(int oldMeta, BlockState state) {
        int i = oldMeta & 1;
        return new Pair<>(oldMeta >> 1, state.withProperty(HALF,
                i == 0 ? BlockSlab.EnumBlockHalf.TOP : BlockSlab.EnumBlockHalf.BOTTOM));
    }

    @Override
    public BlockState onPlacement(BlockState state, World worldIn, BlockPos pos,
                                   Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
        return facing != Direction.DOWN && (facing == Direction.UP || (double) hitY <= 0.5D) ?
                state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM) :
                state.withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
