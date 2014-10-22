/*
 * This file is part of Avaricia.
 *
 * Copyright (c) 2014 Kohle <http://kohle.co>
 * Avaricia is licensed under the Mozilla Public License 2.0.
 *
 * Avaricia is free software: you can redistribute it and/or modify
 * it under the terms of the Mozilla Public License as published by
 * the Mozilla project.
 *
 * Avaricia is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY. See the license for more details.
 *
 * You should have received a copy of the Mozilla Public License 2.0
 * along with this program.  If not, see <https://www.mozilla.org/MPL/2.0/>.
 */

package co.kohle.avaricia;

import co.kohle.avaricia.events.Death;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Avaricia extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Enabled.");

        getServer().getPluginManager().registerEvents(new Death(this), this);

        doConfig();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Disabled.");
    }

    public void doConfig() {
        FileConfiguration config = getConfig();

        config.addDefault("drop.item", "GOLD_INGOT");
        config.addDefault("drop.amount", 10);
        config.options().copyDefaults(true);

        saveConfig();
    }
}
