package pw.tales.fairy.block.scholar_table;

import net.minecraft.block.BlockState;
import net.minecraft.state.Property<?>;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureHRotation;

import java.util.ArrayList;
import java.util.List;

class TwoSidedOpenFeature extends Feature {
    public static BooleanProperty OPEN_FORWARD = BooleanProperty.create("open_forward");
    public static BooleanProperty OPEN_OPPOSITE = BooleanProperty.create("open_opposite");
    public static EnumProperty<OpenStatus> OPEN_STATUS = EnumProperty.create("open_status", OpenStatus.class);

    private final List<Property<?>> properties = new ArrayList<>();

    public TwoSidedOpenFeature() {
        super();
        properties.add(OPEN_FORWARD);
        properties.add(OPEN_OPPOSITE);
        properties.add(OPEN_STATUS);
    }

    private BlockState updateStatus(BlockState state) {
        boolean forward = state.getValue(OPEN_FORWARD);
        boolean opposite = state.getValue(OPEN_OPPOSITE);
        OpenStatus openStatus = OpenStatus.find(forward, opposite);
        if (openStatus == null) return state;
        return state.withProperty(OPEN_STATUS, openStatus);
    }

    @Override
    public ActionResultType onUse(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
        Direction blockFacing = state.getValue(FeatureHRotation.FACING);

        if (facing == blockFacing) {
            state = state.cycleProperty(OPEN_FORWARD);
        }

        if (facing == blockFacing.getOpposite()) {
            state = state.cycleProperty(OPEN_OPPOSITE);
        }

        return worldIn.setBlockState(pos, this.updateStatus(state));
    }

    private int putToMeta(int oldMeta, BlockState state) {
        oldMeta = oldMeta << 1 | (state.getValue(OPEN_FORWARD) ? 1 : 0);
        oldMeta = oldMeta << 1 | (state.getValue(OPEN_OPPOSITE) ? 1 : 0);

        return oldMeta;
    }

    private Pair<Integer, BlockState> getFromMeta(int oldMeta, BlockState state) {
        state = state.withProperty(OPEN_OPPOSITE, (oldMeta & 1) == 1);
        oldMeta = oldMeta >> 1;
        state = state.withProperty(OPEN_FORWARD, (oldMeta & 1) == 1);
        oldMeta = oldMeta >> 1;

        return new Pair<>(oldMeta, this.updateStatus(state));
    }

    @Override
    public BlockState updateDefaultState(BlockState state) {
        state = super.updateDefaultState(state);
        state = state.withProperty(OPEN_FORWARD, false);
        state = state.withProperty(OPEN_OPPOSITE, false);
        return this.updateStatus(state);
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
