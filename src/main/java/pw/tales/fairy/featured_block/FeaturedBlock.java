package pw.tales.fairy.featured_block;


import com.google.common.collect.Lists;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.state.Property<?>;
import net.minecraft.block.BlockStateContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.features.Feature;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
public abstract class FeaturedBlock extends Block {
    /*
    It is set up in createBlockState as it's needed by this function
    but there is no way to set up before it.
     */
    private List<Feature> features;

    public FeaturedBlock(Material material) {
        this(material, material.getMaterialMapColor());

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(OPEN, false)
                        .setValue(HINGE, DoorHingeSide.LEFT)
                        .setValue(POWERED, false)
                        .setValue(HALF, DoubleBlockHalf.LOWER)
        );

    }

    public FeaturedBlock(Material material, MapColor mapColor) {
        super(material, mapColor);

        BlockState state = this.getDefaultState();
        for (Feature feature : features) {
            state = feature.getDefaultState(state);
        }

        this.setDefaultState(state);
    }

    public abstract List<Feature> getFeatures();

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public BlockState getActualState(BlockState state, IBlockAccess worldIn, BlockPos pos) {
        for (Feature feature : features) {
            state = feature.getActualState(state, this, worldIn, pos);
        }
        return state;
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public BlockState getStateForPlacement(@Nullable World world, @Nullable BlockPos pos,
                                            @Nullable Direction facing, float hitX, float hitY, float hitZ, int meta,
                                            @Nullable LivingEntity placer) {
        BlockState state = this.getDefaultState();

        for (Feature feature : features) {
            state = feature.onPlacement(state, world, pos, facing, hitX, hitY, hitZ, meta, placer);
        }

        return state;
    }

    @Override
    @ParametersAreNonnullByDefault
    public ActionResultType use(
            BlockState state,
            World worldIn,
            BlockPos pos,
            PlayerEntity player,
            Hand handIn,
            BlockRayTraceResult hit
    ) {
        ActionResultType f = super.use(state, worldIn, pos, player, handIn, hit);

        if (worldIn.isClientSide()) return ActionResultType.PASS;

        for (Feature feature : features) {
            ActionResultType result = feature.onUse(worldIn, pos, state, player, handIn, hit);

            if (result == ActionResultType.FAIL) {
                f = result;
            }

        }

        return ActionResultType.PASS;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        this.features = this.getFeatures();

        ArrayList<Property<?>> properties = new ArrayList<>();
        for (Feature feature : features) {
            properties.addAll(feature.getProperties());
        }

        return new BlockStateContainer(this, properties.toArray(new Property<?>[0]));
    }
}
