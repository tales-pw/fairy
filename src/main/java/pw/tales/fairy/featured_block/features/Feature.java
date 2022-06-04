package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.Property;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

@MethodsReturnNonnullByDefault
public abstract class Feature {
    public BlockState onPlacement(BlockState state, BlockItemUseContext itemUseContext) {
        return state;
    }

    public ActionResultType onUse(World worldIn, BlockPos pos, BlockState state,
                                  PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
        return ActionResultType.PASS;
    }

    public abstract List<Property<?>> getProperties();

    public BlockState updateDefaultState(BlockState state) {
        return state;
    }

    public BlockState getActualState(BlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        return state;
    }
}
