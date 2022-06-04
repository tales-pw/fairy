package pw.tales.fairy.featured_block.features;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class Feature360Rotation extends Feature {
    public static final Feature360Rotation DEFAULT = new Feature360Rotation();
    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 15);

    private final static List<Property<?>> properties = Collections.singletonList(Feature360Rotation.ROTATION);

    @Override
    public BlockState onPlacement(BlockState state, World worldIn, BlockPos pos,
                                   Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
        float rotation = MathHelper.floor((placer.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
        return state.withProperty(ROTATION, (int) rotation);
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }
}
