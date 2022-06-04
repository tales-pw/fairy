package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockWithFire extends BlockSimple {
    private static final AxisAlignedBB BOUNDING_BOX =
            new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 1D, 0.9D);

    @Override
    public boolean isFullBlock(BlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(BlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    public BlockWithFire() {
        super(Material.IRON);
    }


    @Override
    public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public int getLightValue(BlockState state, IBlockAccess world, BlockPos pos) {
        return 15;
    }
}
