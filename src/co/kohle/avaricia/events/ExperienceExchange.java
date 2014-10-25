/*
 * This file is part of Avaricia.
 *
 * Copyright (c) 2014 Kohle (http://kohle.co)
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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ExperienceExchange implements Listener {

    public Avaricia plugin;

    public ExperienceExchange(Avaricia instance) {
        plugin = instance;
    }

    @EventHandler
    public void onSignCreate(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (event.getBlock().getState() instanceof Sign) {
            if (event.getLine(0).equalsIgnoreCase("[XP]")) {
                if (player.hasPermission("avaricia.xp.create")) {
                    if (event.getLine(1).isEmpty() && event.getLine(2).isEmpty()) {
                        player.sendMessage(ChatColor.RED + "You must specify the exchange rate!");
                        event.setLine(0, null);
                    } else {
                        event.setLine(0, "[XP]");
                        player.sendMessage(ChatColor.GOLD + "XP exchange sign created!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have permission!");
                    event.setLine(0, null);
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0).equalsIgnoreCase("[XP]")) {
                    String itemCostString = sign.getLine(1);
                    String xpLevelString = sign.getLine(2);
                    int itemCost = Integer.parseInt(itemCostString);
                    int xpLevel = Integer.parseInt(xpLevelString);
                    Material material = Material.getMaterial(plugin.getConfig().getString("drop.item"));
                    if (player.getInventory().contains(material, itemCost)) {
                        player.giveExpLevels(xpLevel);
                        player.getInventory().removeItem(new ItemStack(material, itemCost));
                        player.updateInventory();
                        player.sendMessage(ChatColor.GOLD + "Successfully exchanged " + itemCost + " " + material.toString() + " for " + xpLevel + " levels of XP.");
                    } else {
                        player.sendMessage(ChatColor.RED + "You need at least " + itemCost + " " + material.toString() + ".");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have permission!");
                }
            }
        }
    }
}