package net.crsimple.bottledsouls.compat;

import net.crsimple.bottledsouls.ModMain;
import net.fabricmc.loader.api.FabricLoader;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ExecutionException;

public class LuckPermsCompat {
    private static LuckPerms luckPerms;
    private static boolean isEnable;

    public static void init() {
        if(FabricLoader.getInstance().isModLoaded("luckperms")) {
            isEnable = true;
            reloadLuckPerms();
        }
    }

    public static boolean checkPermissionIfEnable(PlayerEntity p, Perm perm) {
        if (!isEnable()) return false;
        return checkPermission(p,perm);
    }
    public static boolean checkPermission(PlayerEntity p, Perm perm) {
        return checkPermission(p,perm.string);
    }
    public static boolean checkPermission(@Nullable PlayerEntity p, String perm) {
        checkLuckPerms();

        if (p == null) return false;
        return getUser(p).getCachedData().getPermissionData().checkPermission(perm).asBoolean();
    }
    public static boolean isEnable() {
        return isEnable;
    }

    private static User getUser(PlayerEntity p) {
        User user = luckPerms.getUserManager().getUser(p.getUuid());

        if (user == null) {
            try {
                user = luckPerms.getUserManager().loadUser(p.getUuid()).get();
            } catch (InterruptedException | ExecutionException e) {
                ModMain.LOGGER.error("failed to load LuckPerms user");
                throw new RuntimeException(e);
            }
        }

        return user;
    }

    private static void checkLuckPerms() {
        if (!isEnable()) throw new IllegalStateException("LuckPerms is not loaded");

        if(luckPerms == null) {
            reloadLuckPerms();
        }
    }
    private static void reloadLuckPerms() {
        try {
            luckPerms = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            ModMain.LOGGER.warn("Bottled Souls - failed to initialize LuckPerms: \n{}",e.getMessage());
        }
    }

    public enum Perm {
        CONFIG_RELOADING_PERM("config.reload");

        public final String string;

        Perm(String perm) {
            this.string = ModMain.MOD_ID + "." + perm;
        }
    }
}
