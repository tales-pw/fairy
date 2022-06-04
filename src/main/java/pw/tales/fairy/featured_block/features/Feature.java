package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.Property;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

@MethodsReturnNonnullByDefault
public abstract class Feature {
    public BlockState onPlacement(BlockState state, World worldIn, BlockPos pos,
                                   Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
        return state;
    }

    public ActionResultType onUse(World worldIn, BlockPos pos, BlockState state,
                                  PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
        return ActionResultType.PASS;
    }

    public abstract List<Property<?>> getProperties();

    public BlockState getDefaultState(BlockState state) {
        return state;
    }

    public BlockState getActualState(BlockState state, Block block, IBlockAccess world,
                                      BlockPos pos) {
        return state;
    }
}
