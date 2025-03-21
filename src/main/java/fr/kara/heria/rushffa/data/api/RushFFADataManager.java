package fr.kara.heria.rushffa.data.api;

import fr.heriamc.api.data.PersistentDataManager;
import fr.heriamc.api.data.mongo.MongoConnection;
import fr.heriamc.api.data.redis.RedisConnection;
import fr.kara.heria.rushffa.config.BlockEnum;
import fr.kara.heria.rushffa.config.EffectEnum;
import fr.kara.heria.rushffa.config.SongEnum;
import fr.kara.heria.rushffa.data.api.kit.PlayerKits;

import java.util.UUID;

public class RushFFADataManager extends PersistentDataManager<UUID, RushFFAData> {

    public RushFFADataManager(RedisConnection redisConnection, MongoConnection mongoConnection) {
        super(redisConnection, mongoConnection, "rushffa", "rushffa");
    }

    @Override
    public RushFFAData getDefault() {
        return new RushFFAData(null, BlockEnum.SANDSTONE.getId(), SongEnum.PLING.getSong(), EffectEnum.NULL.getId(), 0,0, 0, 0, new PlayerKits());
    }

}
