package pw.tales.fairy.block.divine;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
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
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos,
                                            EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}
