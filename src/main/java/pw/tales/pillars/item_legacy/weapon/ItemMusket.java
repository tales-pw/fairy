package pw.tales.pillars.item_legacy.weapon;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;
import pw.tales.pillars.item_legacy.posed.IPoseEnum;
import pw.tales.pillars.item_legacy.posed.IPoseManager;
import pw.tales.pillars.item_legacy.posed.ItemPosed;

@MethodsReturnNonnullByDefault
public class ItemMusket extends ItemPosed<ItemMusket.Pose> {
    public ItemMusket() {
        super(Pose.MARCH);
    }

    @Override
    public ResourceLocation getModelForPose(Pose pose) {
        if (pose == Pose.MARCH)
            return this.getModelVariant("marching");

        return super.getModelForPose(pose);
    }

    public enum Pose implements IPoseManager<Pose>, IPoseEnum<Pose> {
        MARCH, AIM;

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
                return MARCH;
            return VALUES[id];
        }

        @Override
        public int getAmount() {
            return VALUES.length;
        }
    }
}
