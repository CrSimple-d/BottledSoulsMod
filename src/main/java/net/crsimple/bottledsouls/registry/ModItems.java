package net.crsimple.bottledsouls.registry;

import net.crsimple.bottledsouls.ModMain;
import net.crsimple.bottledsouls.common.item.SoulBottleItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class ModItems {
    public static Item SOUL_BOTTLE = reg(SoulBottleItem::new,new Item.Settings()
            .maxDamage(ModMain.CONFIG.isSoulBottleUnbreakable?0:ModMain.CONFIG.soulBottleDurability)
            .rarity(Rarity.UNCOMMON),"soul_bottle",ModGroups.DEFAULT_GROUP_INSTANCE);

    static Item reg(Function<Item.Settings, Item> factory, Item.Settings settings, String id, ItemGroup... groups) {
        Item instance = factory.apply(settings);
        for (var group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group).register(g -> g.add(instance.getDefaultStack()));
        }
        return Registry.register(Registries.ITEM, ModMain.idOf(id), instance);
    }

    public static void init() {
    }
}
