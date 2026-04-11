package net.crsimple.bottledsouls.registry;

import net.crsimple.bottledsouls.ModMain;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public sealed interface ModTags permits ModTags.EntityTags {
    final class EntityTags implements ModTags {
        public static TagKey<EntityType<?>> BLACK_LIST = reg("black_list");

        public static TagKey<EntityType<?>> reg(String id) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, ModMain.idOf(id));
        }
    }
}
