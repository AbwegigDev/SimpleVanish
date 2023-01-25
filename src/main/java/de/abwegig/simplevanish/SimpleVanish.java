package de.abwegig.simplevanish;

import de.abwegig.simplevanish.commands.VanishCommand;
import de.abwegig.simplevanish.commands.VanishListCommand;
import de.abwegig.simplevanish.listener.PlayerJoinListener;
import de.abwegig.simplevanish.permissions.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class SimpleVanish extends JavaPlugin {

    private final String prefix = "§bSimpleVanish §8» §7";

    private final List<Player> vanishedPlayer = new ArrayList<>();

    private Permissions permissions;

    @Override
    public void onEnable() {
        this.logInformationStart();
        this.registerCommands();
        this.registerListener();
    }

    private void registerListener() {
        new PlayerJoinListener(this);
    }


    private void registerCommands() {
        new VanishCommand(this);
        new VanishListCommand(this);
    }

    @Override
    public void onDisable() {
        logInformationStop();
    }

    //log information
    private void logInformationStart() {
        getLogger().log(Level.FINE, "Das Plugin SimpleVanish wurde geladen!");
    }

    private void logInformationStop() {
        getLogger().log(Level.FINE, "Das Plugin SimpleVanish wurde gestoppt!");
    }

    //getter
    public String getPrefix() {
        return prefix;
    }

    public List<Player> getVanishedPlayer() {
        return vanishedPlayer;
    }

    public Permissions getPermissions() {
        return permissions;
    }
}
