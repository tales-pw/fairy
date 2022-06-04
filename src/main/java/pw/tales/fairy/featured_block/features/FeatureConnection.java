package pw.tales.fairy.featured_block.features;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IWorld;

public abstract class FeatureConnection extends Feature {
    protected Vector3i createDirVector(Direction facing1, Direction facing2) {
        Vector3i dir1 = facing1.getNormal();
        Vector3i dir2 = facing2.getNormal();

        return new Vector3i(
                dir1.getX() + dir2.getX(),
                dir1.getY() + dir2.getY(),
                dir1.getZ() + dir2.getZ()
        );
    }

    protected boolean canConnectTo(
            IWorld worldIn,
            BlockState state1,
            BlockPos pos,
            Direction facing1,
            Direction facing2
    ) {
        return this.canConnectTo(worldIn, state1, pos, this.createDirVector(facing1, facing2));
    }

    protected boolean canConnectTo(IWorld worldIn, BlockState state1, BlockPos pos,
                                   Direction facing) {
        return this.canConnectTo(worldIn, state1, pos, facing.getNormal());
    }

    protected boolean canConnectTo(IWorld worldIn, BlockState state1, BlockPos pos,
                                   Vector3i directionalVector) {
        BlockState state2 = worldIn.getBlockState(pos.offset(directionalVector));
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
