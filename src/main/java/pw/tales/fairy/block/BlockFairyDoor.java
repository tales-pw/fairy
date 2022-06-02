package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.registries.ObjectRegistry;

import java.util.Random;

@MethodsReturnNonnullByDefault
public class BlockFairyDoor extends BlockDoor implements IBlockFairy {
    private Item item = null;

    public BlockFairyDoor(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(ObjectRegistry.FAIRY_TAB);
        this.translucent = true;
    }


    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (state.getValue(HALF) == EnumDoorHalf.UPPER)
            return Items.AIR;

        return this.getFairyItem();
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this.getFairyItem());
    }

    private Item getFairyItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, Direction face) {
        return BlockFaceShape.SOLID;
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, Direction facing) {
        return true;
    }

    @Override
    public BlockFairyDoor setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setTranslationKey(name);
        return this;
    }
}
