package com.bmod.util.mixinUtils;

public interface LightmapAccess {
    boolean darkness_isDirty();

    float darkness_prevFlicker();
}