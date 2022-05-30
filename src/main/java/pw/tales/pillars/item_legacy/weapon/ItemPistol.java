package pw.tales.pillars.item_legacy.weapon;

import org.apache.commons.lang3.ArrayUtils;
import pw.tales.pillars.item_legacy.posed.IPoseEnum;
import pw.tales.pillars.item_legacy.posed.IPoseManager;
import pw.tales.pillars.item_legacy.posed.ItemPosed;

public class ItemPistol extends ItemPosed<ItemPistol.Pose> {
    public ItemPistol() {
        super(Pose.LOWER);
    }

    public enum Pose implements IPoseManager<Pose>, IPoseEnum<Pose> {
        LOWER, AIM;

        private static Pose[] VALUES = Pose.values();

        @Override
        public int getId() {
            return ArrayUtils.indexOf(VALUES, this);
        }

        @Override
        public Pose cycle() {
            int current_id = getId();

            return fromId((current_id + 1) % VALUES.length);
        }

        @Override
        public Pose fromId(int id) {
            if (id > VALUES.length)
                return Pose.LOWER;
            return VALUES[id];
        }

        @Override
        public int getAmount() {
            return VALUES.length;
        }
    }
}
