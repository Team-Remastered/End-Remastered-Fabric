package com.endremastered.endrem.world.util;

import com.endremastered.endrem.config.ERConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;

import java.util.ArrayList;
import java.util.List;

public class CustomMonsterSpawn {
    private final int min;
    private final int max;
    private final double factor = switch (ERConfig.getData().MONSTER_DIFFICULTY) {
        case "peaceful" -> 0;
        case "easy" -> 0.5;
        case "hard" -> 2;
        case "hardcore" -> 3;
        default -> 1;
    };
    private final int weight;

    public final EntityType<?> monsterEntity;

    public CustomMonsterSpawn(EntityType<?> monsterEntityIn, int weightIn, int minIn, int maxIn) {
        this.monsterEntity = monsterEntityIn;
        this.min = minIn;
        this.max = maxIn;
        this.weight = weightIn;
    }

    public SpawnSettings.SpawnEntry getIndividualMobSpawnInfo() {
        return new SpawnSettings.SpawnEntry(monsterEntity, this.weight, (int) (this.min * this.factor), (int) (this.max * this.factor));
    }

    public static Pool<SpawnSettings.SpawnEntry> getPoolFromList (List<CustomMonsterSpawn> customSpawnList) {
        List<SpawnSettings.SpawnEntry> spawnList = new ArrayList<>();
            for (CustomMonsterSpawn entry : customSpawnList) {
                spawnList.add(entry.getIndividualMobSpawnInfo());
            }
            return Pool.of(spawnList);
    }
}
