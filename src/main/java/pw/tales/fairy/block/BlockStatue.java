package pw.tales.fairy.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockStatue extends BlockSimple {
    public BlockStatue() {
        super(Material.ROCK);
    }

    @Override
    public int getLightValue(BlockState state, IBlockAccess world, BlockPos pos) {
        return 1;
    }
}
