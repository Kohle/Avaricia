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

package co.kohle.ricomuerte.events;

import co.kohle.ricomuerte.RicoMuerte;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EventListener;

public class Death implements Listener {

    public RicoMuerte plugin;

    public Death(RicoMuerte instance) {
        plugin = instance;
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        if((event.getEntity() instanceof Player) /*&& (event.getEntity().getKiller() instanceof Player)*/) {
            Player victim = event.getEntity().getPlayer();
            Player killer = event.getEntity().getKiller();

            Location location = victim.getLocation();

            Material material = Material.getMaterial(plugin.getConfig().getString("drop.item"));
            int amount = plugin.getConfig().getInt("drop.amount");

            victim.getWorld().dropItemNaturally(location, new ItemStack(material, amount));
        }
    }
}
