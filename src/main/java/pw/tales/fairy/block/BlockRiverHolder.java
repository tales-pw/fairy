package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.registries.ObjectRegistry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class BlockRiverHolder extends BlockBarrier
        implements IBlockFairy {
    public static final AxisAlignedBB EMPTY = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

    public BlockRiverHolder() {
        super();
        this.setCreativeTab(ObjectRegistry.FAIRY_TAB);
    }

    public final BlockRiverHolder setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setTranslationKey(name);
        return this;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean causesSuffocation(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn,
                                                 BlockPos pos) {
        return NULL_AABB;
    }

    @SuppressWarnings("deprecation")
    @Override
    @ParametersAreNonnullByDefault
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        AxisAlignedBB selectedBoundingBox = super.getSelectedBoundingBox(state, worldIn, pos);

        if (!worldIn.isRemote)
            return selectedBoundingBox;

        if (Minecraft.getMinecraft().player.isCreative()) {
            return selectedBoundingBox;
        } else
            return EMPTY;
    }
}
