package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockBarrel
        extends BlockSimple {
    public BlockBarrel() {
        super(Material.WOOD);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, BlockState state, BlockPos pos,
                                            Direction face) {
        return BlockFaceShape.UNDEFINED;
    }
}
