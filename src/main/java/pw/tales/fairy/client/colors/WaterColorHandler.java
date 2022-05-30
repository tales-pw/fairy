package pw.tales.fairy.client.colors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class WaterColorHandler implements IBlockColor {
    public static WaterColorHandler INSTANCE = new WaterColorHandler();

    @ParametersAreNonnullByDefault
    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn,
                               @Nullable BlockPos pos, int tintIndex) {
        return worldIn != null && pos != null ?
                BiomeColorHelper.getWaterColorAtPos(worldIn, pos) :
                -1;
    }
}
