package net.crsimple.bottledsouls.registry;

import net.crsimple.bottledsouls.ModMain;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static ComponentType<NbtComponent> ENTITY_DATA = reg("entity_data",builder -> builder.codec(NbtComponent.CODEC).packetCodec(NbtComponent.PACKET_CODEC));

    static <T> ComponentType<T> reg(String id, UnaryOperator<ComponentType.Builder<T>> builder) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, ModMain.idOf(id), builder.apply(ComponentType.builder()).build());
    }

    public static void init() {
    }
}
