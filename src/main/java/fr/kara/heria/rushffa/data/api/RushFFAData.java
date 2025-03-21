package fr.kara.heria.rushffa.data.api;

import fr.heriamc.api.data.SerializableData;
import fr.kara.heria.rushffa.data.api.kit.PlayerKits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class RushFFAData implements SerializableData<UUID> {

    private UUID id;
    private String actualBlock, actualSong, actualEffect;
    private int point, kills, deaths, recordKS;
    private PlayerKits playerKits;

    @Override
    public UUID getIdentifier() {
        return this.id;
    }

    @Override
    public void setIdentifier(UUID uuid) {
        this.id = uuid;
    }
}
