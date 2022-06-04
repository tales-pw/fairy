package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@MethodsReturnNonnullByDefault
public class FeatureSwitch extends FeatureStoreFlag {
    public FeatureSwitch(String name) {
        super(name);
    }

    public FeatureSwitch(BooleanProperty property) {
        super(property);
    }

    @Override
    public ActionResultType onUse(World worldIn, BlockPos pos, BlockState state,
                                  PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
        boolean result = worldIn.setBlockAndUpdate(pos, state.cycle(property));

        if (!result) {
            return ActionResultType.FAIL;
        }

        return ActionResultType.SUCCESS;
    }
}
