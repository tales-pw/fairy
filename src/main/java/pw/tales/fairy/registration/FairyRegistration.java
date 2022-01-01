package pw.tales.fairy.registration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import pw.tales.fairy.block.IBlockFairy;
import pw.tales.fairy.item.IItemFairy;
import pw.tales.fairy.registration.events.FairyRegisterEvent;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

public class FairyRegistration {
    private static FairyRegistration instance = null;

    private List<IBlockFairy> blocks = new ArrayList<>();
    private List<IItemFairy> items = new ArrayList<>();

    public FairyRegistration() {
    }

    public static FairyRegistration getInstance() {
        if (instance == null) instance = new FairyRegistration();
        return instance;
    }

    public void init() {
        EVENT_BUS.register(this);

        FairyRegisterEvent event = new FairyRegisterEvent();
        EVENT_BUS.post(event);

        this.blocks.addAll(event.getBlocks());
        this.items.addAll(event.getItems());
    }

    @SubscribeEvent
    public void addBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        for (IBlockFairy block : blocks) {
            registry.register(block.getBlock());
        }
    }

    @SubscribeEvent
    public void addItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        for (IBlockFairy block : blocks) {
            items.add(block.createItem());
        }

        for (IItemFairy object : items) {
            registry.register(object.getItem());
        }
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        for (IItemFairy object : items) {
            object.registerModels();
        }
    }
}
