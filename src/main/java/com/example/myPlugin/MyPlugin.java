package com.example.myPlugin;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    public static Economy econ;
    public static MyPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        if (!setupEconomy()) {
            getLogger().severe("Vault not found or no economy plugin. Disabling.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register commands
        getCommand("redeem").setExecutor(new RedeemCommand());
        getCommand("generatecode").setExecutor(new RedeemCommand());

        // Save default config if needed
        saveDefaultConfig();
        saveResource("codes.yml", false);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = rsp.getProvider();
        return econ != null;
    }
    public double getRewardAmount() {
        return getConfig().getDouble("reward-amount", 1000.0);
    }
}
