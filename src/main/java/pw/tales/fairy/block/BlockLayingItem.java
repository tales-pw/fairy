package pw.tales.fairy.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.item.IItemFairy;
import pw.tales.fairy.item.ItemPosedFBlock;
import pw.tales.pillars.item.ItemPoseManager;

import javax.annotation.ParametersAreNonnullByDefault;

public class BlockLayingItem extends BlockSimple {
    protected static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.6D, 0.75D);
    private final ItemPoseManager poses;

    public BlockLayingItem(Material material) {
        super(material);
        this.poses = null;
    }

    public BlockLayingItem(Material material, ItemPoseManager book_poses) {
        super(material);
        this.poses = book_poses;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos,
                                            Direction face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return DEFAULT_AABB;
    }

    @Override
    @ParametersAreNonnullByDefault
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public IItemFairy createItem() {
        if (this.poses == null) return super.createItem();
        return new ItemPosedFBlock(this, this.poses);
    }
}
