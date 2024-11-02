package com.bmod.util.mixinUtils;

import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.UUID;

public interface IEntityDataSaver {
    CompoundTag blubbysmod$getPersistentData();
    HashMap<UUID, CompoundTag> blubbysmod$uuidCompoundTag = new HashMap<>();
}
