package pw.tales.fairy.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import pw.tales.fairy.featured.features.Feature;
import pw.tales.fairy.featured.features.FeatureHRotation;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockSimple extends BlockFairy {
    public BlockSimple(Material material) {
        super(material);
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
        return Collections.singletonList(FeatureHRotation.DEFAULT);
    }
}
