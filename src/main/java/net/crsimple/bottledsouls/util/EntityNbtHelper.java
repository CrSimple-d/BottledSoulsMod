package net.crsimple.bottledsouls.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EntityNbtHelper {
    public static String TYPE_KEY = "Type";

    public static NbtCompound saveEntity(NbtCompound nbt, Entity e) {
        nbt.putString(TYPE_KEY,Registries.ENTITY_TYPE.getId(e.getType()).toString());
        return e.writeNbt(nbt);
    }

    @SuppressWarnings("unchecked")
    public static @NotNull <T extends Entity> T readEntity(World world, NbtCompound nbt, Class<T> clazz) {
        Entity entity = readEntity(world,nbt);
        if ((!clazz.isInstance(entity))) {
            throw new IllegalArgumentException("Wrong class");
        }
        return (T) entity;
    }
    public static @NotNull Entity readEntity(World world, NbtCompound nbt) {
        checkNbt(nbt);

        Entity entity = Registries.ENTITY_TYPE.get(parseIdentifier(nbt)).create(world);
        if (entity == null) throw new IllegalStateException("Entity creating error");
        entity.readNbt(nbt);

        return entity;
    }

    public static @NotNull Identifier parseIdentifier(NbtCompound nbt) {
        checkNbt(nbt);

        Optional<Identifier> id = Optional.ofNullable(Identifier.tryParse(nbt.getString(TYPE_KEY)));
        return id.orElseThrow(() -> new IllegalStateException("failed to parse Identifier from " + nbt.getString(TYPE_KEY)));
    }

    private static void checkNbt(NbtCompound nbt) {
        if(!hasEntity(nbt)) {
            throw new IllegalArgumentException("nbt is not valid");
        }
    }

    public static boolean hasEntity(@Nullable NbtCompound nbt) {
        return nbt != null && !nbt.isEmpty() && nbt.contains(TYPE_KEY);
    }
}
