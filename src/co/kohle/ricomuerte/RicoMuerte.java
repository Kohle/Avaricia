/*
 * This file is part of RicoMuerte.
 *
 * Copyright (c) 2014 Kohle <http://kohle.co>
 * RicoMuerte is licensed under the Mozilla Public License 2.0.
 *
 * RicoMuerte is free software: you can redistribute it and/or modify
 * it under the terms of the Mozilla Public License as published by
 * the Mozilla project.
 *
 * RicoMuerte is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY. See the license for more details.
 *
 * You should have received a copy of the Mozilla Public License 2.0
 * along with this program.  If not, see <https://www.mozilla.org/MPL/2.0/>.
 */

package co.kohle.ricomuerte;

import co.kohle.ricomuerte.events.Death;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class RicoMuerte extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Enabled.");
        getServer().getPluginManager().registerEvents(new Death(), this);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Disabled.");
    }
}
