package pw.tales.pillars.pose_render;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import pw.tales.pillars.ModelPoseRegistry;
import pw.tales.pillars.pose_definition.ModelPose;
import pw.tales.pillars.pose_definition.SidePose;

public class ModelPoseRenderer {
    private final ModelPoseRegistry registry;

    public ModelPoseRenderer(ModelPoseRegistry registry) {
        this.registry = registry;
    }

    public void updateRotation(EntityLivingBase entity, ModelRenderer bipedRightArm,
                               ModelRenderer bipedLeftArm) {
        ItemStack rightItemStack, leftItemStack;
        PoseState state = new PoseState();

        HandSide primaryHand = entity.getPrimaryHand();
        if (primaryHand == HandSide.RIGHT) {
            rightItemStack = entity.getHeldItem(Hand.MAIN_HAND);
            leftItemStack = entity.getHeldItem(Hand.OFF_HAND);
        } else {
            rightItemStack = entity.getHeldItem(Hand.OFF_HAND);
            leftItemStack = entity.getHeldItem(Hand.MAIN_HAND);
        }

        ModelPose rPose = registry.getPose(rightItemStack.getItem(), rightItemStack.getMetadata());
        if (rPose != null) {
            updateSideRotation(rPose.right, HandSide.RIGHT, bipedRightArm, bipedLeftArm, state);
        }

        ModelPose lPose = registry.getPose(leftItemStack.getItem(), leftItemStack.getMetadata());
        if (lPose != null) {
            updateSideRotation(lPose.left, HandSide.RIGHT, bipedRightArm, bipedLeftArm, state);
        }
    }

    public void updateSideRotation(SidePose pose, HandSide side, ModelRenderer bipedRightArm,
                                   ModelRenderer bipedLeftArm, PoseState state) {
        if (pose.right_arm != null && !state.ownedRight) {
            applyArmRotation(bipedRightArm, pose.right_arm);
            if (side == HandSide.RIGHT)
                state.ownedRight = true;
        }

        if (pose.left_arm != null && !state.ownedLeft) {
            applyArmRotation(bipedLeftArm, pose.left_arm);
            if (side == HandSide.LEFT)
                state.ownedLeft = true;
        }
    }

    public void applyArmRotation(ModelRenderer bipedArm, float[] angles) {
        bipedArm.rotateAngleX = -(float) Math.toRadians(angles[0]);
        bipedArm.rotateAngleY = -(float) Math.toRadians(angles[1]);
        bipedArm.rotateAngleZ = (float) Math.toRadians(angles[2]);
    }
}
