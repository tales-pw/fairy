package pw.tales.pillars.item;

import org.apache.commons.lang3.ArrayUtils;

public class ItemPoseManager {
    private String[] poseIds;

    public ItemPoseManager(String[] poseIds) {
        if (poseIds.length == 0)
            throw new IllegalArgumentException("You should have at least one pose.");

        this.poseIds = poseIds;
    }

    public int getMeta(String pose) {
        return ArrayUtils.indexOf(poseIds, pose);
    }

    public String next(String pose) {
        int poseIndex = ArrayUtils.indexOf(poseIds, pose);
        if (poseIndex == -1)
            return poseIds[0];
        int newIndex = poseIndex + 1;
        return this.fromMeta(newIndex);
    }

    public String fromMeta(int meta) {
        int index = meta % poseIds.length;
        return poseIds[index];
    }

    public int getAmount() {
        return this.poseIds.length;
    }

    public static PoseListBuilder builder() {
        return new PoseListBuilder();
    }
}
