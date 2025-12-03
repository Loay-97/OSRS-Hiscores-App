package com.osrs.hiscores.mapping;

import java.util.HashMap;
import java.util.Map;

public class ItemIdLookup {

    private static final Map<String, Integer> items = new HashMap<>();

    static {
        items.put("abyssal whip", 4151);
        items.put("dragon scimitar", 4587);
        items.put("armadyl godsword", 11802);
        items.put("bandos chestplate", 11832);
        items.put("zamorakian spear", 11824);
        items.put("dragon claws", 13652);
        items.put("toxic blowpipe", 12926);
        items.put("bandos godsword", 11804);
        items.put("dragon hunter crossbow", 21012);
        items.put("dragon hunter lance", 22978);

        // Voeg hierboven alle andere items toe, in lowercase
    }

    public static Integer getItemId(String name) {
        return items.get(name.toLowerCase());
    }

    public static boolean exists(String name) {
        return items.containsKey(name.toLowerCase());
    }

    // Optioneel: lijst van alle itemnamen ophalen
    public static String[] getAllItemNames() {
        return items.keySet().toArray(new String[0]);
    }
}
