package pw.tales.fairy.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureColor;
import pw.tales.fairy.item.IItemFairy;
import pw.tales.fairy.item.ItemFCloth;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockVial extends BlockFairy {
    public BlockVial() {
        super(Material.IRON);
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos,
                                            EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(FeatureColor.COLOR).getMetadata();
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
            ItemStack itemStack = new ItemStack(this, 1, enumdyecolor.getMetadata());
            items.add(itemStack);
        }
    }

    @Override
    public List<Feature> getFeatures() {
        return Collections.singletonList(FeatureColor.DEFAULT);
    }

    @Override
    public IItemFairy createItem() {
        return new ItemFCloth(this);
    }
}
