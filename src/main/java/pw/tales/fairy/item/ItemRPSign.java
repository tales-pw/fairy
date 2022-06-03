package pw.tales.fairy.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.block.BlockRPSign;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class ItemRPSign extends ItemFBlock {
    public ItemRPSign(BlockRPSign block) {
        super(block);
    }

    private BlockPos determinePosition(World worldIn, BlockPos pos, Direction facing) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(worldIn, pos)) {
            pos = pos.offset(facing);
        }

        return pos;
    }

    @ParametersAreNonnullByDefault
    public ActionResultType onItemUse(PlayerEntity player, World worldIn, BlockPos pos, Hand hand, Direction facing, float hitX, float hitY, float hitZ) {
        ActionResultType result = super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);

        if (worldIn.isRemote || result != ActionResultType.SUCCESS)
            return result;

        pos = this.determinePosition(worldIn, pos, facing);
        ItemStack itemstack = player.getItemInHand(hand);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!BlockItem.setTileEntityNBT(worldIn, player, pos, itemstack)) {
            player.openEditSign((TileEntitySign) tileentity);
        }

        return result;
    }
}

