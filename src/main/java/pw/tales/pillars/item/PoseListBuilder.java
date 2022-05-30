package pw.tales.pillars.item;

import java.util.ArrayList;
import java.util.List;

public class PoseListBuilder {
    private List<String> poses = new ArrayList<>();

    public PoseListBuilder add(String pose) {
        poses.add(pose);
        return this;
    }

    public ItemPoseManager build() {
        return new ItemPoseManager(poses.toArray(new String[0]));
    }
}
