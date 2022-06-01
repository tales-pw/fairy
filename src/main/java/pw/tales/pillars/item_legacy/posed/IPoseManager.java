package pw.tales.pillars.item_legacy.posed;

public interface IPoseManager<T extends IPoseEnum<T>> {
    T fromId(int id);

    int getAmount();
}
