package us.centile.practice.util;

import org.bukkit.plugin.java.*;
import java.io.*;
import org.bukkit.configuration.file.*;

public class Config
{
    private FileConfiguration config;
    private File file;
    private String name;
    
    public Config(final JavaPlugin plugin, final String path, final String name) {
        (this.file = new File(plugin.getDataFolder() + path)).mkdirs();
        this.file = new File(plugin.getDataFolder() + path, name + ".yml");
        try {
            this.file.createNewFile();
        }
        catch (IOException ex) {}
        this.name = name;
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
    }
    
    public String getName() {
        return this.name;
    }
    
    public FileConfiguration getConfig() {
        return this.config;
    }
    
    public void setDefault(final String Path, final Object Set) {
        if (!this.getConfig().contains(Path)) {
            this.config.set(Path, Set);
            this.save();
        }
    }
    
    public void reload() {
        try {
            this.config.load(this.file);
        }
        catch (Exception ex) {}
    }
    
    public void save() {
        try {
            this.config.save(this.file);
        }
        catch (IOException ex) {}
    }
}
