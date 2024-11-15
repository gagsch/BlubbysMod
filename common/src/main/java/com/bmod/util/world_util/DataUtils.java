package com.bmod.util.world_util;

import com.bmod.BlubbysMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataUtils extends SavedData {

    private static final String DATA_NAME = BlubbysMod.MOD_ID + "data";
    private final Map<UUID, CompoundTag> playerTags = new HashMap<>();

    public DataUtils() {}

    public static DataUtils load(CompoundTag nbt) {
        DataUtils data = new DataUtils();

        for (String key : nbt.getAllKeys()) {
            CompoundTag tag = nbt.getCompound(key);

            data.playerTags.put(UUID.fromString(key), tag);
        }

        return data;
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag compoundTag) {
        for (Map.Entry<UUID, CompoundTag> entry : playerTags.entrySet()) {
            compoundTag.put(entry.getKey().toString(), entry.getValue());
        }
        return compoundTag;
    }

    public CompoundTag getPlayerTags(UUID key) {
        return playerTags.getOrDefault(key, new CompoundTag());
    }

    public void replaceAllTags(UUID player, CompoundTag value) {
        playerTags.put(player, value);
        this.setDirty();
    }

    public void putTag(UUID player, String key, CompoundTag value) {
        playerTags.computeIfAbsent(player, k -> new CompoundTag()).put(key, value);
        this.setDirty();
    }

    public void putBoolean(UUID player, String key, boolean value) {
        playerTags.computeIfAbsent(player, k -> new CompoundTag()).putBoolean(key, value);
        this.setDirty();
    }

    public static DataUtils getCustomWorldData(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                DataUtils::load,
                DataUtils::new,
                DATA_NAME
        );
    }
}