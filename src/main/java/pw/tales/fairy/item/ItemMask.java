package pw.tales.fairy.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import pw.tales.pillars.item.ItemModelWrapper;
import pw.tales.pillars.item.ItemPoseManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;

@MethodsReturnNonnullByDefault
public class ItemMask extends ItemWearable {
    private ItemModelWrapper itemModelMediator;

    public ItemMask(ItemPoseManager poseManager) {
        super();
        this.itemModelMediator = new ItemModelWrapper(this, poseManager);
        this.setHasSubtypes(true);
    }

    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
                                                    Hand handIn) {
        this.itemModelMediator.onItemRightClick(playerIn, handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return this.itemModelMediator.getTranslationKey(stack);
    }

    @Override
    public void getSubItems(ItemGroup tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(this.getColorSubItem(Color.yellow));
            items.add(this.getColorSubItem(Color.white));
            items.add(this.getColorSubItem(Color.red));
            items.add(this.getColorSubItem(Color.pink));
            items.add(this.getColorSubItem(Color.green));
            items.add(this.getColorSubItem(Color.blue));
            items.add(this.getColorSubItem(Color.black));
        }
    }

    private ItemStack getColorSubItem(Color color) {
        ItemStack itemStack = new ItemStack(this, 1);
        NBTTagCompound tagCompound = new NBTTagCompound();

        NBTTagCompound displayCompound = new NBTTagCompound();
        displayCompound.setInteger("color", color.getRGB());

        tagCompound.setTag("display", displayCompound);

        itemStack.setTagCompound(tagCompound);
        return itemStack;
    }

    @Override
    public void registerModels() {
        this.itemModelMediator.registerModels();
    }
}
