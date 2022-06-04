package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.Fairy;

@MethodsReturnNonnullByDefault
public class BlockMiddleDoor extends BlockFairyDoor {
    protected static final AxisAlignedBB OPEN_NORTH_LEFT_AABB = new AxisAlignedBB(
            -0.0625D, 0.0D, -0.5D,
            0.0625D, 1.0D, 0.5D
    );
    protected static final AxisAlignedBB OPEN_NORTH_RIGHT_AABB = OPEN_NORTH_LEFT_AABB.offset(1, 0, 0);
    protected static final AxisAlignedBB OPEN_SOUTH_LEFT_AABB = OPEN_NORTH_RIGHT_AABB.offset(0, 0, 1);
    protected static final AxisAlignedBB OPEN_SOUTH_RIGHT_AABB = OPEN_NORTH_LEFT_AABB.offset(0, 0, 1);

    protected static final AxisAlignedBB OPEN_EAST_LEFT_AABB = new AxisAlignedBB(
            0.5D, 0.0D, -0.0625D,
            1.5D, 1.0D, 0.0625D
    );
    protected static final AxisAlignedBB OPEN_EAST_RIGHT_AABB = OPEN_EAST_LEFT_AABB.offset(0, 0, 1);
    protected static final AxisAlignedBB OPEN_WEST_LEFT_AABB = OPEN_EAST_RIGHT_AABB.offset(-1, 0, 0);
    protected static final AxisAlignedBB OPEN_WEST_RIGHT_AABB = OPEN_EAST_LEFT_AABB.offset(-1, 0, 0);

    protected static final AxisAlignedBB X_AABB = new AxisAlignedBB(
            0.0D, 0.0D, 0.4375D,
            1.0D, 1.0D, 0.5625D
    );
    protected static final AxisAlignedBB Z_AABB = new AxisAlignedBB(
            0.4375D, 0.0D, 0.0D,
            0.5625D, 1.0D, 1.0D
    );

    public BlockMiddleDoor(Material materialIn) {
        super(materialIn);
    }

    @Override
    public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {
        state = state.getActualState(source, pos);

        Direction Direction = state.getValue(FACING);
        boolean isNotOpen = !state.getValue(OPEN);
        boolean isRHinge = state.getValue(HINGE) == EnumHingePosition.RIGHT;

        switch (Direction) {
            case EAST:
            default:
                return isNotOpen ? Z_AABB : (isRHinge ? OPEN_EAST_RIGHT_AABB : OPEN_EAST_LEFT_AABB);
            case SOUTH:
                return isNotOpen ? X_AABB : (isRHinge ? OPEN_SOUTH_RIGHT_AABB : OPEN_SOUTH_LEFT_AABB);
            case WEST:
                return isNotOpen ? Z_AABB : (isRHinge ? OPEN_WEST_RIGHT_AABB : OPEN_WEST_LEFT_AABB);
            case NORTH:
                return isNotOpen ? X_AABB : (isRHinge ? OPEN_NORTH_RIGHT_AABB : OPEN_NORTH_LEFT_AABB);
        }
    }

    @Override
    public EnumBlockRenderType getRenderType(BlockState state) {
        if (state.getValue(BlockDoor.HALF) == EnumDoorHalf.UPPER)
            return EnumBlockRenderType.INVISIBLE;
        return super.getRenderType(state);
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, Direction facing) {
        return true;
    }

}
