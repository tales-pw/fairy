package pw.tales.fairy.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.block.BlockRPSign;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class ItemRPSign extends ItemFBlock {
    public ItemRPSign(BlockRPSign block) {
        super(block);
    }

    private BlockPos determinePosition(World worldIn, BlockPos pos, EnumFacing facing) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(worldIn, pos)) {
            pos = pos.offset(facing);
        }

        return pos;
    }

    @ParametersAreNonnullByDefault
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EnumActionResult result = super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);

        if (worldIn.isRemote || result != EnumActionResult.SUCCESS)
            return result;

        pos = this.determinePosition(worldIn, pos, facing);
        ItemStack itemstack = player.getHeldItem(hand);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!ItemBlock.setTileEntityNBT(worldIn, player, pos, itemstack)) {
            player.openEditSign((TileEntitySign) tileentity);
        }

        return result;
    }
}

