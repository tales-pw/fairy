package pw.tales.pillars.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class ItemPosed extends Item {
    private final ItemModelMediator itemModelMediator;

    public ItemPosed(ResourceLocation resourceLocation, ItemPoseManager poseManager) {
        super(new Properties());
        this.setRegistryName(resourceLocation);
        this.itemModelMediator = new ItemModelMediator(this, poseManager);
    }

    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        this.itemModelMediator.onItemRightClick(player, hand);
        return super.use(world, player, hand);
    }
}
