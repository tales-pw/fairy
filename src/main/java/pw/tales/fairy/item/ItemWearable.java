package pw.tales.fairy.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.registries.ItemsRegistry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class ItemWearable extends BlockItem implements IItemFairy {
    public ItemWearable() {
        super(new Block(Material.AIR).setCreativeTab(ItemsRegistry.FAIRY_MISC_TAB));
        this.setMaxStackSize(1);
    }

    @ParametersAreNonnullByDefault
    public ActionResultType onItemUse(PlayerEntity player, World worldIn, BlockPos pos,
                                      Hand hand, Direction facing, float hitX, float hitY, float hitZ) {
        return ActionResultType.PASS;
    }

    public void registerModels() {
        Fairy.proxy.setUpItemVariant(this, 0, "inventory");
    }

    public ItemWearable setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setTranslationKey(name);
        return this;
    }

    @Override
    @ParametersAreNonnullByDefault
    public Item setTranslationKey(String translationKey) {
        this.block.setTranslationKey(translationKey);
        return this;
    }

    @Nullable
    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.HEAD;
    }
}
