package pw.tales.fairy.block.scholar_table;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.block.BlockFairy;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureHRotation;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockScholarTable extends BlockFairy {
    public static final Feature TWO_SIDED_FEATURE = new TwoSidedOpenFeature();

    public BlockScholarTable() {
        super(Material.WOOD);
    }

    @Override
    public int getLightValue(BlockState state, IBlockAccess world, BlockPos pos) {
        return 1;
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
        return Arrays.asList(FeatureHRotation.DEFAULT, TWO_SIDED_FEATURE);
    }

}
