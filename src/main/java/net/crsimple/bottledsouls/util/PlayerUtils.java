package net.crsimple.bottledsouls.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;

public class PlayerUtils {
    public static void damageUnlessCreative(PlayerEntity p, ItemStack stack, int i, EquipmentSlot slot) {
        if (!p.isCreative() && stack.isDamageable() && p instanceof ServerPlayerEntity) {
            stack.damage(i,() -> Items.AIR,p,slot);
        }
    }
    public static void damageUnlessCreative(PlayerEntity p, ItemStack stack, Hand hand) {
        damageUnlessCreative(p,stack,1,handToSlot(hand));
    }

    private static EquipmentSlot handToSlot(Hand hand) {
        return hand == Hand.OFF_HAND?EquipmentSlot.OFFHAND:EquipmentSlot.MAINHAND;
    }
}
