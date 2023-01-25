package de.abwegig.simplevanish.commands;

import de.abwegig.simplevanish.SimpleVanish;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class VanishCommand implements CommandExecutor {

    private final SimpleVanish plugin;

    public VanishCommand(@NotNull SimpleVanish plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("vanish")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (player.hasPermission("nytou.vanish.use") || player.hasPermission("nytou.cb.admin")) {
                if (plugin.getVanishedPlayer().contains(player)) {
                    plugin.getVanishedPlayer().remove(player);
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        all.showPlayer(plugin, player);
                    });
                    player.sendMessage(plugin.getPrefix() + "§7Du bist nun §cnicht mehr §7im Vanish!");
                    //aus
                } else {
                    plugin.getVanishedPlayer().add(player);
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        if (!all.hasPermission("nytou.cb.vanish.bypass") || player.hasPermission("nytou.cb.admin")) {
                            all.hidePlayer(plugin, player);
                        }
                    });
                    player.sendMessage(plugin.getPrefix() + "§7Du bist nun §aim Vanish!");
                    //an
                }
            } else {
                player.sendMessage(plugin.getPrefix() + "§7Du hast §ckeine §7Berechtigungen diesen Command zu benutzen!");
            }
        }


        return false;
    }
}
