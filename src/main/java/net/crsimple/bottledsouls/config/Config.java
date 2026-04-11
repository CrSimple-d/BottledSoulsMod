package net.crsimple.bottledsouls.config;

import java.util.List;

public class Config {
    public final boolean isSoulBottleUnbreakable = false;
    public final int soulBottleDurability = 5;
    public final ActivableCollection<String> mobBlackList;
    public final ActivableCollection<String> mobWhiteList;

    public Config(ActivableCollection<String> mobBlackList, ActivableCollection<String> mobWhiteList) {
        this.mobBlackList = mobBlackList;
        this.mobWhiteList = mobWhiteList;
    }
    public Config() {
        this(new ActivableCollection<>(), new ActivableCollection<>(){{collection.addAll(List.of("warden", "wither"));}});
    }
}
