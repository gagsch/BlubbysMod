package me.blubby.bmod.core.util.dlUtils;

public interface LightmapAccess {
    boolean darkness_isDirty();

    float darkness_prevFlicker();
}