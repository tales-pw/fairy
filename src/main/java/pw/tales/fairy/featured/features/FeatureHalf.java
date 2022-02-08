package pw.tales.fairy.featured.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.featured.Pair;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault public class FeatureHalf extends Feature {
    public static final Feature DEFAULT = new FeatureHalf();

    public static final PropertyEnum<BlockSlab.EnumBlockHalf> HALF =
        PropertyEnum.<BlockSlab.EnumBlockHalf>create("half", BlockSlab.EnumBlockHalf.class);

    private static final List<IProperty> properties = new ArrayList<>();

    static {
        properties.add(HALF);
    }

    @Override public IBlockState getDefaultState(IBlockState state) {
        return super.getDefaultState(state).withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
    }

    @Override public int putToMeta(int oldMeta, IBlockState state) {
        return oldMeta << 1 | (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? 0 : 1);
    }

    @Override public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        int i = oldMeta & 1;
        return new Pair<>(oldMeta >> 1, state.withProperty(HALF,
            i == 0 ? BlockSlab.EnumBlockHalf.TOP : BlockSlab.EnumBlockHalf.BOTTOM));
    }

    @Override public IBlockState onPlacement(IBlockState state, World worldIn, BlockPos pos,
        EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D) ?
            state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM) :
            state.withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
    }

    @Override public List<IProperty> getProperties() {
        return properties;
    }
}
