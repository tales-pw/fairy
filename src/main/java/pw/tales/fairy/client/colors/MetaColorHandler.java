package pw.tales.fairy.client.colors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured.features.FeatureColor;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class MetaColorHandler implements IItemColor, IBlockColor {
    public static MetaColorHandler DEFAULT = new MetaColorHandler();

    @ParametersAreNonnullByDefault
    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn,
                               @Nullable BlockPos pos, int tintIndex) {
        return state.getValue(FeatureColor.COLOR).getColorValue();
    }

    @ParametersAreNonnullByDefault
    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        return EnumDyeColor.byMetadata(stack.getMetadata()).getColorValue();
    }
}
