package pw.tales.fairy.featured_block;


import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
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
    }

    public FeaturedBlock(Material material, MapColor mapColor) {
        super(material, mapColor);

        BlockState state = this.stateDefinition.any();
        for (Feature feature : features) {
            state = feature.updateDefaultState(state);
        }

        this.registerDefaultState(state);
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

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public BlockState getStateForPlacement(BlockItemUseContext itemUseContext) {
        BlockState state = this.defaultBlockState();

        for (Feature feature : features) {
            state = feature.onPlacement(state, itemUseContext);
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

        return f;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateContainerBuilder) {
        super.createBlockStateDefinition(stateContainerBuilder);

        this.features = this.getFeatures();

        for (Feature feature : features) {
            for (Property<?> property : feature.getProperties()) {
                stateContainerBuilder.add(property);
            }
        }
    }
}
