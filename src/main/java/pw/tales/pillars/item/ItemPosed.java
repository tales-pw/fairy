package pw.tales.pillars.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class ItemPosed extends Item {
    private ItemModelWrapper itemModelMediator;

    public ItemPosed(ResourceLocation resourceLocation, ItemPoseManager poseManager) {
        this.setRegistryName(resourceLocation);
        this.setTranslationKey(resourceLocation.getPath());
        itemModelMediator = new ItemModelWrapper(this, poseManager);
    }

    public ItemPosed(ResourceLocation resourceLocation, String... poses) {
        this(resourceLocation, new ItemPoseManager(poses));
    }

    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
                                                    EnumHand handIn) {
        this.itemModelMediator.onItemRightClick(playerIn, handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return this.itemModelMediator.getTranslationKey(stack);
    }

    public void registerModels() {
        this.itemModelMediator.registerModels();
    }
}
