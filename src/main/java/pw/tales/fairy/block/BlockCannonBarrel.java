package pw.tales.fairy.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured.features.Feature;
import pw.tales.fairy.featured.features.FeatureFlag;
import pw.tales.fairy.featured.features.FeatureHRotation;
import pw.tales.fairy.featured.features.FeatureHalf;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockCannonBarrel extends BlockFairy
        implements FeatureFlag.IFlagHandler {
    public BlockCannonBarrel() {
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
    public boolean isOpaqueCube(IBlockState state) {
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
    public boolean handleFlag(String name, IBlockState state, Block block, IBlockAccess world,
                              BlockPos pos) {
        return this.checkIsHanging(state, world, pos);
    }

    private boolean checkIsHanging(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(FeatureHalf.HALF) == BlockSlab.EnumBlockHalf.BOTTOM)
            return false;
        return world.getBlockState(pos.up()).getMaterial().isSolid();
    }
}
