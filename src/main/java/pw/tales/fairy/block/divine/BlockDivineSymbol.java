package pw.tales.fairy.block.divine;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.block.BlockSimple;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockDivineSymbol
        extends BlockSimple {
    public BlockDivineSymbol() {
        super(Material.IRON);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, BlockState state, BlockPos pos,
                                            Direction face) {
        return BlockFaceShape.UNDEFINED;
    }
}
