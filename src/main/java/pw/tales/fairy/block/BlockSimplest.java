package pw.tales.fairy.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.registration.registries.ObjectRegistry;

public class BlockSimplest extends Block implements IBlockFairy {
    public BlockSimplest(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(ObjectRegistry.FAIRY_TAB);
    }

    @Override
    public final BlockSimplest setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setTranslationKey(name);
        return this;
    }
}
