package pw.tales.fairy.featured_block.features;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;

public abstract class FeatureConnection extends Feature {
    protected Vec3i createDirVector(Direction facing1, Direction facing2) {
        Vec3i dir1 = facing1.getDirectionVec();
        Vec3i dir2 = facing2.getDirectionVec();

        return new Vec3i(dir1.getX() + dir2.getX(), dir1.getY() + dir2.getY(),
                dir1.getZ() + dir2.getZ());
    }

    protected boolean canConnectTo(IBlockAccess worldIn, BlockState state1, BlockPos pos,
                                   Direction facing1, Direction facing2) {
        return this.canConnectTo(worldIn, state1, pos, this.createDirVector(facing1, facing2));
    }

    protected boolean canConnectTo(IBlockAccess worldIn, BlockState state1, BlockPos pos,
                                   Direction facing) {
        return this.canConnectTo(worldIn, state1, pos, facing.getDirectionVec());
    }

    protected boolean canConnectTo(IBlockAccess worldIn, BlockState state1, BlockPos pos,
                                   Vec3i directionalVector) {
        BlockState state2 = worldIn.getBlockState(pos.add(directionalVector));
        return this.canConnectTo(state1, state2);
    }

    public boolean canConnectTo(BlockState state1, BlockState state2) {
        IConnectible connectible1 = (IConnectible) state1.getBlock();
        return this.canConnectTo(connectible1, state2.getBlock());
    }

    public boolean canConnectTo(IConnectible block1, Block block2) {
        return block1.canConnect(block2);
    }

    public interface IConnectible {
        boolean canConnect(Block block2);
    }
}
