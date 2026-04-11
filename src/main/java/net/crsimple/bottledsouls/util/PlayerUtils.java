package net.crsimple.bottledsouls.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;

public class PlayerUtils {
    public static void damageUnlessCreative(PlayerEntity p, ItemStack stack, int i) {
        if (!p.isCreative() && stack.isDamageable() && p instanceof ServerPlayerEntity sp) {
            stack.damage(i,sp,$p -> $p.sendToolBreakStatus(ItemStack.areEqual($p.getMainHandStack(),stack)? Hand.MAIN_HAND:Hand.OFF_HAND));
        }
    }
    public static void damageUnlessCreative(PlayerEntity p, ItemStack stack) {
        damageUnlessCreative(p,stack,1);
    }
}
