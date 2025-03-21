package fr.kara.heria.rushffa.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SpawnLocation {
    HUB(new Location(Bukkit.getWorld("world"), 1.5, 88, 236.5, -180, 2)),
    SPEC(new Location(Bukkit.getWorld("world"), 0.5, 80, 0.5, 90, 90)),

    // Spawn
    SPAWN(new Location(Bukkit.getWorld("world"), 23.5, 64, 72.5, -179, 0)),
    SPAWN1(new Location(Bukkit.getWorld("world"), 43.5, 64, 66.5, 179, 0)),
    SPAWN2(new Location(Bukkit.getWorld("world"), 66.5, 64, 43.5, 90, 0)),
    SPAWN3(new Location(Bukkit.getWorld("world"), 72.5, 64, 23.5, 90, -1)),
    SPAWN4(new Location(Bukkit.getWorld("world"), 76, 64, 0.5, 90, -0)),
    SPAWN5(new Location(Bukkit.getWorld("world"), 72.5, 64, -22.5, 90, 0)),
    SPAWN6(new Location(Bukkit.getWorld("world"), 66.5, 64, -42.5, 90, 0)),
    SPAWN7(new Location(Bukkit.getWorld("world"), 43.5, 64, -65.5, 0, 0)),
    SPAWN8(new Location(Bukkit.getWorld("world"), 23.5, 64, -71.5, 0, 0)),
    SPAWN9(new Location(Bukkit.getWorld("world"), 0.5, 64, -75, 0, -0)),
    SPAWN10(new Location(Bukkit.getWorld("world"), -22.5, 64, -71.5, 0, 0)),
    SPAWN11(new Location(Bukkit.getWorld("world"), -42.5, 64, -65.5, 0, 0)),
    SPAWN12(new Location(Bukkit.getWorld("world"), -65.5, 64, -42.5, -90, 0)),
    SPAWN13(new Location(Bukkit.getWorld("world"), -71.5, 64, -22.5, -90, -0)),
    SPAWN14(new Location(Bukkit.getWorld("world"), -75.5, 64, 0.5, -90, 0)),
    SPAWN15(new Location(Bukkit.getWorld("world"), -71.5, 64, 23.5, -90, -0)),
    SPAWN16(new Location(Bukkit.getWorld("world"), -65.5, 64, 43.5, -90, -0)),
    SPAWN17(new Location(Bukkit.getWorld("world"), -42.5, 64, 66.5, 180, -0)),
    SPAWN18(new Location(Bukkit.getWorld("world"), -22.5, 64, 72.5, -180, -0)),
    SPAWN19(new Location(Bukkit.getWorld("world"), 0.5, 64, 76.5, -180, -0)),
    ;

    private final Location location;

    SpawnLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public static Location getRandomSpawnLocation() {
        List<SpawnLocation> spawnLocations = Stream.of(SpawnLocation.values())
                .filter(loc -> loc.name().contains("SPAWN"))
                .collect(Collectors.toList());

        int randomIndex = ThreadLocalRandom.current().nextInt(spawnLocations.size());
        return spawnLocations.get(randomIndex).getLocation();
    }
}
