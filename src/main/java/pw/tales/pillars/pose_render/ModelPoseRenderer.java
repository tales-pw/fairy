package pw.tales.pillars.pose_render;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
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

        EnumHandSide primaryHand = entity.getPrimaryHand();
        if (primaryHand == EnumHandSide.RIGHT) {
            rightItemStack = entity.getHeldItem(EnumHand.MAIN_HAND);
            leftItemStack = entity.getHeldItem(EnumHand.OFF_HAND);
        } else {
            rightItemStack = entity.getHeldItem(EnumHand.OFF_HAND);
            leftItemStack = entity.getHeldItem(EnumHand.MAIN_HAND);
        }

        ModelPose rPose = registry.getPose(rightItemStack.getItem(), rightItemStack.getMetadata());
        if (rPose != null) {
            updateSideRotation(rPose.right, EnumHandSide.RIGHT, bipedRightArm, bipedLeftArm, state);
        }

        ModelPose lPose = registry.getPose(leftItemStack.getItem(), leftItemStack.getMetadata());
        if (lPose != null) {
            updateSideRotation(lPose.left, EnumHandSide.RIGHT, bipedRightArm, bipedLeftArm, state);
        }
    }

    public void updateSideRotation(SidePose pose, EnumHandSide side, ModelRenderer bipedRightArm,
                                   ModelRenderer bipedLeftArm, PoseState state) {
        if (pose.right_arm != null && !state.ownedRight) {
            applyArmRotation(bipedRightArm, pose.right_arm);
            if (side == EnumHandSide.RIGHT)
                state.ownedRight = true;
        }

        if (pose.left_arm != null && !state.ownedLeft) {
            applyArmRotation(bipedLeftArm, pose.left_arm);
            if (side == EnumHandSide.LEFT)
                state.ownedLeft = true;
        }
    }

    public void applyArmRotation(ModelRenderer bipedArm, float[] angles) {
        bipedArm.rotateAngleX = -(float) Math.toRadians(angles[0]);
        bipedArm.rotateAngleY = -(float) Math.toRadians(angles[1]);
        bipedArm.rotateAngleZ = (float) Math.toRadians(angles[2]);
    }
}
