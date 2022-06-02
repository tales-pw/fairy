package pw.tales.fairy.block.scholar_table;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;
import pw.tales.fairy.featured_block.features.Feature;
import pw.tales.fairy.featured_block.features.FeatureHRotation;

import java.util.ArrayList;
import java.util.List;

class TwoSidedOpenFeature extends Feature {
    public static PropertyBool OPEN_FORWARD = PropertyBool.create("open_forward");
    public static PropertyBool OPEN_OPPOSITE = PropertyBool.create("open_opposite");
    public static PropertyEnum<OpenStatus> OPEN_STATUS = PropertyEnum.create("open_status", OpenStatus.class);

    private final List<IProperty> properties = new ArrayList<>();

    public TwoSidedOpenFeature() {
        super();
        properties.add(OPEN_FORWARD);
        properties.add(OPEN_OPPOSITE);
        properties.add(OPEN_STATUS);
    }

    private IBlockState updateStatus(IBlockState state) {
        boolean forward = state.getValue(OPEN_FORWARD);
        boolean opposite = state.getValue(OPEN_OPPOSITE);
        OpenStatus openStatus = OpenStatus.find(forward, opposite);
        if (openStatus == null) return state;
        return state.withProperty(OPEN_STATUS, openStatus);
    }

    @Override
    public boolean onActivated(World worldIn, BlockPos pos, IBlockState state, PlayerEntity playerIn, Hand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EnumFacing blockFacing = state.getValue(FeatureHRotation.FACING);

        if (facing == blockFacing) {
            state = state.cycleProperty(OPEN_FORWARD);
        }

        if (facing == blockFacing.getOpposite()) {
            state = state.cycleProperty(OPEN_OPPOSITE);
        }

        return worldIn.setBlockState(pos, this.updateStatus(state));
    }

    @Override
    public int putToMeta(int oldMeta, IBlockState state) {
        oldMeta = oldMeta << 1 | (state.getValue(OPEN_FORWARD) ? 1 : 0);
        oldMeta = oldMeta << 1 | (state.getValue(OPEN_OPPOSITE) ? 1 : 0);

        return oldMeta;
    }

    @Override
    public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        state = state.withProperty(OPEN_OPPOSITE, (oldMeta & 1) == 1);
        oldMeta = oldMeta >> 1;
        state = state.withProperty(OPEN_FORWARD, (oldMeta & 1) == 1);
        oldMeta = oldMeta >> 1;

        return new Pair<>(oldMeta, this.updateStatus(state));
    }

    @Override
    public IBlockState getDefaultState(IBlockState state) {
        state = super.getDefaultState(state);
        state = state.withProperty(OPEN_FORWARD, false);
        state = state.withProperty(OPEN_OPPOSITE, false);
        return this.updateStatus(state);
    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }
}
