package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;

import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureColor extends Feature {
    public static final Feature DEFAULT = new FeatureColor();

    public static final PropertyEnum<EnumDyeColor> COLOR =
            PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);

    private static final List<IProperty> properties = new ArrayList<>();

    static {
        properties.add(COLOR);
    }

    @Override
    public IBlockState getDefaultState(IBlockState state) {
        return super.getDefaultState(state).withProperty(COLOR, EnumDyeColor.WHITE);
    }

    @Override
    public int putToMeta(int oldMeta, IBlockState state) {
        return oldMeta << 4 | state.getValue(COLOR).getMetadata();
    }

    @Override
    public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        return new Pair<>(oldMeta >> 4,
                state.withProperty(COLOR, EnumDyeColor.byMetadata(oldMeta & 15)));
    }

    @Override
    public IBlockState onPlacement(IBlockState state, World worldIn, BlockPos pos,
                                   Direction facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return state.withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }
}
