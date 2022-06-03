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
            return this.getFirst();

        int newIndex = poseIndex + 1;
        return poseIds[newIndex % poseIds.length];
    }

    public int getAmount() {
        return this.poseIds.length;
    }

    public static PoseListBuilder builder() {
        return new PoseListBuilder();
    }

    public String getFirst() {
        return poseIds[0];
    }
}
