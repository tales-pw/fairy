package pw.tales.fairy.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureFlag;
import pw.tales.fairy.featured_block.features.FeatureHRotation;
import pw.tales.fairy.featured_block.features.FeatureHalf;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockCannonBarrel extends BlockFairy
        implements FeatureFlag.IFlagHandler {
    public BlockCannonBarrel() {
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
    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();
        features.add(FeatureHRotation.DEFAULT);
        features.add(FeatureHalf.DEFAULT);
        features.add(new FeatureFlag("hanging"));
        return features;
    }

    @Override
    public boolean handleFlag(String name, BlockState state, Block block, IBlockAccess world,
                              BlockPos pos) {
        return this.checkIsHanging(state, world, pos);
    }

    private boolean checkIsHanging(BlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(FeatureHalf.HALF) == BlockSlab.EnumBlockHalf.BOTTOM)
            return false;
        return world.getBlockState(pos.up()).getMaterial().isSolid();
    }
}
