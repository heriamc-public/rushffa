package fr.kara.heria.rushffa.scorboard;

import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.data.PlayerInfo;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PersonalScoreboard {
    private final Player player;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;

    public PersonalScoreboard(Player player) {
        this.player = player;
        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "HeriaMC");
        reloadData();
        objectiveSign.addReceiver(player);
    }

    public void reloadData() {
    }

    public void setLines(String ip) {
        final PlayerInfo data = PlayerInfo.getPlayerData(player);
        RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(player.getUniqueId());

        objectiveSign.setDisplayName("§e» §6§lRushFFA §e«");

        objectiveSign.setLine(0, "§1");
        objectiveSign.setLine(1, "§8■ §7Points : §6" + rushFFAData.getPoint());
        objectiveSign.setLine(2, "§2");
        objectiveSign.setLine(3, "§8■ §7Tué(s) : §a" + rushFFAData.getKills());
        objectiveSign.setLine(4, "§8■ §7Mort(s) : §c" + rushFFAData.getDeaths());
        objectiveSign.setLine(5, "§3");
        objectiveSign.setLine(6, "§8■ §7Ratio : §e" + data.getRatio());
        objectiveSign.setLine(7, "§8■ §7KillStreak : §b" + data.getKs());
        objectiveSign.setLine(8, "§8■ §7RecordKS  : §3" + rushFFAData.getRecordKS());
        objectiveSign.setLine(9, "§4");
        objectiveSign.setLine(10, "§8■ §7Connectés : §b" + Bukkit.getOnlinePlayers().size());
        objectiveSign.setLine(11, "§5");
        objectiveSign.setLine(12, ip);

        objectiveSign.updateLines();
    }

    public void onLogout() {
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}

