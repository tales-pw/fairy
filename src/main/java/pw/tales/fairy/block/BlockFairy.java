package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.featured_block.FeaturedBlock;
import pw.tales.fairy.registries.ObjectRegistry;

@MethodsReturnNonnullByDefault
public abstract class BlockFairy extends FeaturedBlock implements IBlockFairy {
    public BlockFairy(Material material) {
        super(material);
        this.setCreativeTab(ObjectRegistry.FAIRY_TAB);
    }

    public final BlockFairy setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setTranslationKey(name);
        return this;
    }
}
