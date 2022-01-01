package pw.tales.fairy.block.scholar_table;

import net.minecraft.util.IStringSerializable;

enum OpenStatus implements IStringSerializable {
    CLOSED("closed", false, false),
    FORWARD("forward", true, false),
    OPPOSITE("opposite", false, true),
    BOTH("both", true, true);

    private final String name;
    private final boolean forward;
    private final boolean opposite;

    OpenStatus(String name, boolean forward, boolean opposite) {
        this.name = name;
        this.forward = forward;
        this.opposite = opposite;
    }

    static OpenStatus find(boolean forward, boolean opposite) {
        for (OpenStatus value : OpenStatus.values()) {
            if (value.forward == forward && value.opposite == opposite) return value;
        }

        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
