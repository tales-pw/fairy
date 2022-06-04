package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.Property<?>;
import net.minecraft.state.EnumProperty;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureHRotation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
public class BlockLargeDoor
        extends BlockFairy {
    private final AxisAlignedBB AABB_X;
    private final AxisAlignedBB AABB_Z;

    public BlockLargeDoor(Material material, double height, double width) {
        super(material);

        AABB_X = new AxisAlignedBB(1 - width, 0.0D, 0.3D, width, height, 0.7D);
        AABB_Z = new AxisAlignedBB(0.3D, 0.0D, 1 - width, 0.7, height, width);
    }

    @Override
    public boolean isFullBlock(BlockState state) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(BlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(BlockState state) {
        return false;
    }

    @Override
    public int getLightValue(BlockState state, IBlockAccess world, BlockPos pos) {
        return 1;
    }

    @Override
    public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {
        state = state.getActualState(source, pos);

        Direction facing = state.getValue(FeatureHRotation.FACING);

        switch (facing.getAxis()) {
            case X:
                return AABB_X;
            case Z:
                return AABB_Z;
        }

        return super.getBoundingBox(state, source, pos);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(BlockState state, IBlockAccess worldIn,
                                                 BlockPos pos) {
        OpenStatus status = state.getValue(FeatureOpenStatus.OPEN_STATUS);
        if (status.getIsOpen())
            return NULL_AABB;
        return getBoundingBox(state, worldIn, pos);
    }

    @Override
    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();
        features.add(FeatureHRotation.DEFAULT);
        features.add(FeatureOpenStatus.DEFAULT);
        return features;
    }

    @MethodsReturnNonnullByDefault
    enum OpenStatus implements IStringSerializable {
        IN(0, "in"), CLOSED(1, "closed", false), OUT(2, "out");

        private final int index;
        private final String name;
        private final boolean isOpen;

        OpenStatus(int index, String name) {
            this(index, name, true);
        }

        OpenStatus(int index, String name, boolean isOpen) {
            this.index = index;
            this.name = name;
            this.isOpen = isOpen;
        }

        public static OpenStatus getByIndex(int index) {
            switch (index) {
                case 0:
                    return OpenStatus.IN;
                case 2:
                    return OpenStatus.OUT;
                default:
                    return OpenStatus.CLOSED;
            }
        }

        @Override
        public String getName() {
            return this.name;
        }

        public boolean getIsOpen() {
            return this.isOpen;
        }

        public int getIndex() {
            return this.index;
        }
    }


    @MethodsReturnNonnullByDefault
    static class FeatureOpenStatus extends Feature {
        public static final Feature DEFAULT = new FeatureOpenStatus();
        public static final EnumProperty<OpenStatus> OPEN_STATUS =
                EnumProperty.create("open", OpenStatus.class);

        private int putToMeta(int oldMeta, BlockState state) {
            return oldMeta << 2 | state.getValue(OPEN_STATUS).getIndex();
        }

        private Pair<Integer, BlockState> getFromMeta(int oldMeta, BlockState state) {
            return new Pair<>(oldMeta >> 2,
                    state.withProperty(OPEN_STATUS, OpenStatus.getByIndex(oldMeta & 3)));
        }

        @Override
        public ActionResultType onUse(World worldIn, BlockPos pos, BlockState state,
                                      PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

            OpenStatus status = state.getValue(OPEN_STATUS);
            if (status.getIsOpen()) {
                status = OpenStatus.CLOSED;
            } else {
                Vec3i playerFacing = playerIn.getHorizontalFacing().getDirectionVec();
                Vec3i openFacing =
                        state.getValue(FeatureHRotation.FACING).rotateY().getDirectionVec();

                double distance = playerFacing
                        .getDistance(openFacing.getX(), openFacing.getY(), openFacing.getZ());
                if (distance > 0)
                    status = OpenStatus.OUT;
                else
                    status = OpenStatus.IN;
            }

            return worldIn.setBlockState(pos, state.withProperty(OPEN_STATUS, status));
        }

        @Override
        public List<Property<?>> getProperties() {
            return Collections.singletonList(OPEN_STATUS);
        }

        @Override
        public BlockState getDefaultState(BlockState state) {
            return super.getDefaultState(state).withProperty(OPEN_STATUS, OpenStatus.CLOSED);
        }
    }
}
