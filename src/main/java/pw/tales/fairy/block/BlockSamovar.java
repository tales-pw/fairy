package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured.features.Feature;
import pw.tales.fairy.featured.features.FeatureDefaultValue;
import pw.tales.fairy.featured.features.FeatureHRotation;
import pw.tales.fairy.featured.features.FeatureSwitch;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockSamovar
        extends BlockFairy implements FeatureDefaultValue.IDefaultValueHandler {
    private static final PropertyBool LIT_PROPERTY = PropertyBool.create("lit");
    private static final FeatureSwitch SWITCH_FEATURE = new FeatureSwitch(LIT_PROPERTY);
    private static final FeatureDefaultValue BOOT_FEATURE = new FeatureDefaultValue("boot");

    private static final AxisAlignedBB BOUNDING_BOX =
            new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 2D, 0.8D);

    public BlockSamovar() {
        this(false);
    }

    public BlockSamovar(boolean boot) {
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(LIT_PROPERTY))
            return 8;
        return super.getLightValue(state, world, pos);
    }

    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();
        features.add(FeatureHRotation.DEFAULT);
        features.add(SWITCH_FEATURE);
        features.add(BOOT_FEATURE);
        return features;
    }

    @Override
    public boolean getDefaultValue(String name) {
        return false;
    }
}
