package com.bmod.util.dlUtils;

public interface LightmapAccess {
    boolean darkness_isDirty();

    float darkness_prevFlicker();
}