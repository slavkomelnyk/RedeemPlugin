package com.example.myPlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CodeManager {

    private static File file;
    private static FileConfiguration config;

    static {
        file = new File(MyPlugin.instance.getDataFolder(), "codes.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static boolean isValidCode(String code) {
        return config.contains("codes." + code);
    }

    public static void removeCode(String code) {
        config.set("codes." + code, null);
        save();
    }

    public static void addCode(String code) {
        config.set("codes." + code, true);
        save();
    }

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateCode() {
        String code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        addCode(code);
        return code;
    }
}
