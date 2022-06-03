package pw.tales.pillars.mixin;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
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
    public ModelRenderer bipedRightArm;
    @Shadow
    public ModelRenderer bipedLeftArm;

    @Shadow
    public ModelRenderer bipedHead;

    private void processHand(LivingEntity entityLiving, Hand hand, ModelRenderer model) {
        ItemStack itemStack = entityLiving.getHeldItem(hand);
        Item item = itemStack.getItem();

        if (!(item instanceof ItemPistol))
            return;

        ItemPistol itemPistol = (ItemPistol) item;
        ItemPistol.Pose pose = itemPistol.getPose(itemStack);

        if (pose == ItemPistol.Pose.AIM) {
            model.rotateAngleX = (float) (this.bipedHead.rotateAngleX + Math.toRadians(-90));
        }
    }

    @Inject(method = "setRotationAngles", at = @At("RETURN"))
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity, CallbackInfo ci) {

        if (!(entity instanceof LivingEntity))
            return;

        LivingEntity entityLiving = (LivingEntity) entity;

        processHand(entityLiving, Hand.MAIN_HAND, this.bipedRightArm);
        processHand(entityLiving, Hand.OFF_HAND, this.bipedLeftArm);
    }
}
