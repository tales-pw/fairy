package pw.tales.fairy.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureHRotation;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockSimple extends BlockFairy {
    public BlockSimple(Material material) {
        super(material);
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
        return Collections.singletonList(FeatureHRotation.DEFAULT);
    }
}
