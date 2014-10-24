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

package co.kohle.avaricia.events;

import co.kohle.avaricia.Avaricia;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Death implements Listener {

    public Avaricia plugin;

    public Death(Avaricia instance) {
        plugin = instance;
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        if((event.getEntity() instanceof Player) && (event.getEntity().getKiller() instanceof Player)) {
            Player victim = event.getEntity().getPlayer();
            Player killer = event.getEntity().getKiller();

            Location location = victim.getLocation();

            Material material = Material.getMaterial(plugin.getConfig().getString("drop.item"));
            int amount = plugin.getConfig().getInt("drop.amount");

            Effect effect = Effect.getByName(plugin.getConfig().getString("effect.name"));
            int duration = plugin.getConfig().getInt("effect.duration");

            victim.getWorld().dropItemNaturally(location, new ItemStack(material, amount));
            victim.getWorld().playEffect(location, effect, duration);
        }
    }
}
