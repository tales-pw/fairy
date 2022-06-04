package pw.tales.fairy.block.divine;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockSolSymbol
        extends BlockDivineSymbol {
    protected static final AxisAlignedBB DEFAULT_AABB =
            new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);

    @Override
    public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {
        return DEFAULT_AABB;
    }
}
