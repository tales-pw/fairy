package pw.tales.fairy.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import pw.tales.pillars.item.ItemModelWrapper;
import pw.tales.pillars.item.ItemPoseManager;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class ItemCigarette extends ItemWearable {
    private ItemModelWrapper itemModelMediator;

    public ItemCigarette(ItemPoseManager poseManager) {
        super();
        this.itemModelMediator = new ItemModelWrapper(this, poseManager);
        this.setMaxStackSize(10);
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
    public void registerModels() {
        this.itemModelMediator.registerModels();
    }
}
