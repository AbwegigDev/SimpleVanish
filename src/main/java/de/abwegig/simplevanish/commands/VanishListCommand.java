package de.abwegig.simplevanish.commands;

import de.abwegig.simplevanish.SimpleVanish;
import de.abwegig.simplevanish.permissions.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class VanishListCommand implements CommandExecutor {

    private final SimpleVanish plugin;

    public VanishListCommand(@NotNull SimpleVanish plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("vanishlist")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission(plugin.getPermissions().getVanishListPermission())) {

            if (plugin.getVanishedPlayer().size() >= 1) {
                List<String> names = plugin.getVanishedPlayer().stream().map(HumanEntity::getName).toList();
                String vanishedNames = String.join("§7, §c", names);
                player.sendMessage(plugin.getPrefix() + "§7Spieler im Vanish: §c" + vanishedNames);
            } else {
                player.sendMessage(plugin.getPrefix() + "§7Momentan sind §ckeine §7Spieler im Vanish!");
            }
        } else {
            player.sendMessage(plugin.getPrefix() + "§7Du hast §ckeine §7Berechtigungen diesen Command zu benutzen!");
        }

        return false;
    }
}
