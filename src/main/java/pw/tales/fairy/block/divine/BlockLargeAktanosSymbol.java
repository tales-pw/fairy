package pw.tales.fairy.block.divine;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.featured.features.Feature;
import pw.tales.fairy.featured.features.FeatureHRotation;
import pw.tales.fairy.featured.features.FeatureSwitch;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation") @MethodsReturnNonnullByDefault public class BlockLargeAktanosSymbol
    extends BlockDivineSymbol {
    private static final PropertyBool LIT_PROPERTY = PropertyBool.create("lit");
    private static final FeatureSwitch SWITCH_FEATURE = new FeatureSwitch(LIT_PROPERTY);

    @Override public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(LIT_PROPERTY))
            return 15;
        return super.getLightValue(state, world, pos);
    }

    @Override public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();
        features.add(FeatureHRotation.DEFAULT);
        features.add(SWITCH_FEATURE);
        return features;
    }
}
