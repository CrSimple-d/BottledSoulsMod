package net.crsimple.bottledsouls.registry;

import net.crsimple.bottledsouls.ModMain;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ModGroups {
    //public static RegistryKey<ItemGroup> DEFAULT_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, ModMain.idOf("default"));
    public static ItemGroup DEFAULT_GROUP_INSTANCE = FabricItemGroup.builder(ModMain.idOf("default"))
            .icon(() -> ModItems.SOUL_BOTTLE.getDefaultStack())
            .displayName(Text.translatable("itemGroup.bottled_souls.default"))
            .build();

    public static void init() {
    }
}
