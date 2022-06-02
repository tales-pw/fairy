package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;

import java.util.Collections;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureLayers extends Feature {
    public static final FeatureLayers DEFAULT = new FeatureLayers();
    public static final PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);

    public static void main(String[] args) {
        BlockStateContainer container = new BlockStateContainer(null, LAYERS);
        IBlockState defaultState = DEFAULT.getDefaultState(container.getBaseState());

        int meta_1 = DEFAULT.putToMeta(0, defaultState.withProperty(LAYERS, 1));
        int meta_2 = DEFAULT.putToMeta(0, defaultState.withProperty(LAYERS, 2));
        int meta_3 = DEFAULT.putToMeta(0, defaultState.withProperty(LAYERS, 3));
        int meta_4 = DEFAULT.putToMeta(0, defaultState.withProperty(LAYERS, 4));
        int meta_5 = DEFAULT.putToMeta(0, defaultState.withProperty(LAYERS, 5));
        int meta_6 = DEFAULT.putToMeta(0, defaultState.withProperty(LAYERS, 6));
        int meta_7 = DEFAULT.putToMeta(0, defaultState.withProperty(LAYERS, 7));
        int meta_8 = DEFAULT.putToMeta(0, defaultState.withProperty(LAYERS, 8));

        System.out.println("1: " + meta_1);
        System.out.println("2: " + meta_2);
        System.out.println("3: " + meta_3);
        System.out.println("4: " + meta_4);
        System.out.println("5: " + meta_5);
        System.out.println("6: " + meta_6);
        System.out.println("7: " + meta_7);
        System.out.println("8: " + meta_8);

        System.out
                .println("1: " + DEFAULT.getFromMeta(meta_1, defaultState.withProperty(LAYERS, 1)));
        System.out
                .println("2: " + DEFAULT.getFromMeta(meta_2, defaultState.withProperty(LAYERS, 2)));
        System.out
                .println("3: " + DEFAULT.getFromMeta(meta_3, defaultState.withProperty(LAYERS, 3)));
        System.out
                .println("4: " + DEFAULT.getFromMeta(meta_4, defaultState.withProperty(LAYERS, 4)));
        System.out
                .println("5: " + DEFAULT.getFromMeta(meta_5, defaultState.withProperty(LAYERS, 5)));
        System.out
                .println("6: " + DEFAULT.getFromMeta(meta_6, defaultState.withProperty(LAYERS, 6)));
        System.out
                .println("7: " + DEFAULT.getFromMeta(meta_7, defaultState.withProperty(LAYERS, 7)));
        System.out
                .println("8: " + DEFAULT.getFromMeta(meta_8, defaultState.withProperty(LAYERS, 8)));
    }

    @Override
    public IBlockState getDefaultState(IBlockState state) {
        return state.withProperty(LAYERS, 1);
    }

    @Override
    public int putToMeta(int oldMeta, IBlockState state) {
        return oldMeta << 3 | (state.getValue(LAYERS) - 1);
    }

    @Override
    public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        return new Pair<>(oldMeta >> 3, state.withProperty(LAYERS, (oldMeta & 7) + 1));
    }

    @Override
    public boolean onActivated(World worldIn, BlockPos pos, IBlockState state,
                               PlayerEntity playerIn, Hand hand, Direction facing, float hitX, float hitY,
                               float hitZ) {

        return worldIn.setBlockState(pos, state.cycleProperty(LAYERS));
    }

    @Override
    public List<IProperty> getProperties() {
        return Collections.singletonList(LAYERS);
    }
}
