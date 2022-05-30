package pw.tales.fairy.featured.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pw.tales.fairy.featured.Pair;

import java.util.List;

@MethodsReturnNonnullByDefault
public abstract class Feature {
    public IBlockState onPlacement(IBlockState state, World worldIn, BlockPos pos,
                                   EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return state;
    }

    public int putToMeta(int oldMeta, IBlockState state) {
        return oldMeta;
    }

    public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        return new Pair<>(oldMeta, state);
    }

    public boolean onActivated(World worldIn, BlockPos pos, IBlockState state,
                               EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY,
                               float hitZ) {
        return false;
    }

    public abstract List<IProperty> getProperties();

    public IBlockState getDefaultState(IBlockState state) {
        return state;
    }

    public IBlockState getActualState(IBlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        return state;
    }
}
