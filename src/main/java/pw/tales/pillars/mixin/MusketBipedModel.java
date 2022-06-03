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
import pw.tales.pillars.item_legacy.weapon.ItemMusket;

@Mixin(BipedModel.class)
public abstract class MusketBipedModel {
    @Shadow
    public BipedModel.ArmPose rightArmPose;
    @Shadow
    public BipedModel.ArmPose leftArmPose;
    @Shadow
    public ModelRenderer rightArm;
    @Shadow
    public ModelRenderer leftArm;

    public void processPreHand(LivingEntity entityLiving, Hand hand) {
        ItemStack itemStack = entityLiving.getItemInHand(hand);
        Item item = itemStack.getItem();

        if (!(item instanceof ItemMusket))
            return;

        ItemMusket itemMusket = (ItemMusket) item;
        ItemMusket.Pose pose = itemMusket.getPose(itemStack);

        if (pose == ItemMusket.Pose.AIM) {
            this.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
            this.leftArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
        }
    }

    public void processPostHand(LivingEntity entityLiving, Hand hand, ModelRenderer model) {
        ItemStack itemStack = entityLiving.getItemInHand(hand);
        Item item = itemStack.getItem();

        if (!(item instanceof ItemMusket))
            return;

        ItemMusket itemMusket = (ItemMusket) item;
        ItemMusket.Pose pose = itemMusket.getPose(itemStack);

        if (pose == ItemMusket.Pose.MARCH) {
            model.xRot = (float) Math.toRadians(-45);
        }
    }


    @Inject(method = "setupAnim*", at = @At("HEAD"))
    public void onPreRotationAngles(
            LivingEntity entity,
            float p_225597_2_,
            float p_225597_3_,
            float p_225597_4_,
            float p_225597_5_,
            float p_225597_6_,
            CallbackInfo ci
    ) {
        processPreHand(entity, Hand.MAIN_HAND);
        processPreHand(entity, Hand.OFF_HAND);
    }

    @Inject(method = "setupAnim*", at = @At("RETURN"))
    public void onPostRotationAngles(
            LivingEntity entity,
            float p_225597_2_,
            float p_225597_3_,
            float p_225597_4_,
            float p_225597_5_,
            float p_225597_6_,
            CallbackInfo ci
    ) {
        processPostHand(entity, Hand.MAIN_HAND, this.rightArm);
        processPostHand(entity, Hand.OFF_HAND, this.leftArm);
    }
}
