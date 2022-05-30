package pw.tales.fairy.client.colors;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;

public class MaskItemColors implements IItemColor {
    @Override
    @ParametersAreNonnullByDefault
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        Integer color = getColor(stack);
        if (color == null)
            return Color.WHITE.getRGB();
        return color;
    }

    @Nullable
    public Integer getColor(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();

        if (tagCompound == null)
            return null;

        if (!tagCompound.hasKey("display"))
            return null;

        NBTTagCompound displayCompound = tagCompound.getCompoundTag("display");

        if (!displayCompound.hasKey("color"))
            return null;

        return displayCompound.getInteger("color");
    }
}
