package pw.tales.fairy.featured_block;


import com.google.common.collect.Lists;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
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

        IBlockState state = this.getDefaultState();
        for (Feature feature : features) {
            state = feature.getDefaultState(state);
        }

        this.setDefaultState(state);
    }

    public abstract List<Feature> getFeatures();

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        for (Feature feature : features) {
            state = feature.getActualState(state, this, worldIn, pos);
        }
        return state;
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(@Nullable World world, @Nullable BlockPos pos,
                                            @Nullable Direction facing, float hitX, float hitY, float hitZ, int meta,
                                            @Nullable LivingEntity placer) {
        IBlockState state = this.getDefaultState();

        for (Feature feature : features) {
            state = feature.onPlacement(state, world, pos, facing, hitX, hitY, hitZ, meta, placer);
        }

        return state;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        for (Feature feature : Lists.reverse(features)) {
            Pair<Integer, IBlockState> pair = feature.getFromMeta(meta, state);
            meta = pair.first;
            state = pair.second;
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        for (Feature feature : features) {
            meta = feature.putToMeta(meta, state);
        }
        return meta;
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
                                    PlayerEntity playerIn, Hand hand, Direction facing, float hitX, float hitY,
                                    float hitZ) {
        if (worldIn.isRemote) return true;

        boolean f =
                super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);

        for (Feature feature : features) {
            f |= feature.onActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }

        return f;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        this.features = this.getFeatures();

        ArrayList<IProperty> properties = new ArrayList<>();
        for (Feature feature : features) {
            properties.addAll(feature.getProperties());
        }

        return new BlockStateContainer(this, properties.toArray(new IProperty[0]));
    }
}
