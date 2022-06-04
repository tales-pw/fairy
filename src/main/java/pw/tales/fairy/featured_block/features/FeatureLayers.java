package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

@MethodsReturnNonnullByDefault
public class FeatureLayers extends Feature {
    public static final FeatureLayers DEFAULT = new FeatureLayers();
    public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 1, 8);

    @Override
    public BlockState updateDefaultState(BlockState state) {
        return state.setValue(LAYERS, 1);
    }

    @Override
    public ActionResultType onUse(
            World worldIn,
            BlockPos pos,
            BlockState state,
            PlayerEntity playerIn,
            Hand hand,
            BlockRayTraceResult hit
    ) {
        boolean result = worldIn.setBlockAndUpdate(pos, state.cycle(LAYERS));

        if (!result) {
            return ActionResultType.FAIL;
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public List<Property<?>> getProperties() {
        return Collections.singletonList(LAYERS);
    }
}
