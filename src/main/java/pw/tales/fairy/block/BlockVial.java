package pw.tales.fairy.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
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
    public boolean isFullBlock(BlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(BlockState state) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, BlockState state, BlockPos pos,
                                            Direction face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    @Override
    public int damageDropped(BlockState state) {
        return state.getValue(FeatureColor.COLOR).getId();
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void getSubBlocks(ItemGroup itemIn, NonNullList<ItemStack> items) {
        for (DyeColor DyeColor : DyeColor.values()) {
            ItemStack itemStack = new ItemStack(this, 1, DyeColor.getMetadata());
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
