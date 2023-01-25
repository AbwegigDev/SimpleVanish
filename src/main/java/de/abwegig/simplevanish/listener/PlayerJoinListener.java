package de.abwegig.simplevanish.listener;

import de.abwegig.simplevanish.SimpleVanish;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinListener implements Listener {

    private final SimpleVanish plugin;

    public PlayerJoinListener(@NotNull SimpleVanish plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Bukkit.getOnlinePlayers().forEach(all -> {
            if (this.plugin.getVanishedPlayer().contains(player)) {
                all.hidePlayer(plugin, player);
            }
        });
    }

}
