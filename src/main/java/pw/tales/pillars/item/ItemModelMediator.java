package pw.tales.pillars.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;

public class ItemModelMediator {
    private final static String POSE_TAG = "pillars_pose";

    private final Item item;
    private final ItemPoseManager poses;

    public ItemModelMediator(Item item, ItemPoseManager poses) {
        this.item = item;
        this.poses = poses;
    }

    private String getPose(ItemStack stack) {
        CompoundNBT tag = stack.getTag();

        if (tag == null) {
            return poses.getFirst();
        }

        String pose = tag.getString(POSE_TAG);

        if (pose.isEmpty()) {
            return poses.getFirst();
        }

        return pose;
    }

    private void setPose(ItemStack stack, String pose) {
        CompoundNBT tag = stack.getTag();

        if (tag == null) {
            tag = new CompoundNBT();
        }

        tag.putString(POSE_TAG, pose);

        stack.setTag(tag);
    }

    public void onItemRightClick(PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);

        String pose = this.getPose(stack);
        String newPose = poses.next(pose);
        this.setPose(stack, newPose);
    }
}
