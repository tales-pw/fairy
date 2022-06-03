package pw.tales.pillars.mixin;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.tales.pillars.item_legacy.weapon.ItemPistol;

@Mixin(BipedModel.class)
public abstract class PistolBipedModel {
    @Shadow
    public ModelRenderer rightArm;
    @Shadow
    public ModelRenderer leftArm;
    @Shadow
    public ModelRenderer head;

    private void processHand(LivingEntity entityLiving, Hand hand, ModelRenderer model) {
        ItemStack itemStack = entityLiving.getItemInHand(hand);
        Item item = itemStack.getItem();

        if (!(item instanceof ItemPistol))
            return;

        ItemPistol itemPistol = (ItemPistol) item;
        ItemPistol.Pose pose = itemPistol.getPose(itemStack);

        if (pose == ItemPistol.Pose.AIM) {
            model.xRot = (float) (this.head.xRot + Math.toRadians(-90));
        }
    }

    @Inject(method = "setupAnim*", at = @At("RETURN"))
    public void setRotationAngles(
            LivingEntity entity,
            float p_225597_2_,
            float p_225597_3_,
            float p_225597_4_,
            float p_225597_5_,
            float p_225597_6_,
            CallbackInfo ci
    ) {
        processHand(entity, Hand.MAIN_HAND, this.rightArm);
        processHand(entity, Hand.OFF_HAND, this.leftArm);
    }
}
