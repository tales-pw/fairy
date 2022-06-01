package pw.tales.fairy.featured_block.features;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import pw.tales.fairy.featured_block.Pair;

import java.util.Collections;
import java.util.List;

public class Feature360Rotation extends Feature {
    public static final Feature360Rotation DEFAULT = new Feature360Rotation();
    public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);

    private static List<IProperty> properties =
            Collections.singletonList(Feature360Rotation.ROTATION);

    @Override
    public IBlockState onPlacement(IBlockState state, World worldIn, BlockPos pos,
                                   EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        float rotation = MathHelper.floor((placer.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
        return state.withProperty(ROTATION, (int) rotation);
    }

    @Override
    public Pair<Integer, IBlockState> getFromMeta(int oldMeta, IBlockState state) {
        return new Pair<>(0, state.withProperty(ROTATION, oldMeta));
    }

    @Override
    public int putToMeta(int oldMeta, IBlockState state) {
        return oldMeta << 4 | state.getValue(ROTATION);
    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }
}
