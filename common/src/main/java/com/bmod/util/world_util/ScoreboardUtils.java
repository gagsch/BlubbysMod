package com.bmod.util.world_util;

import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;

public class ScoreboardUtils {
    public static PlayerTeam getOrCreateTeam(Level level, String teamName)
    {
        if (level.getScoreboard().getPlayerTeam(teamName) != null)
            return level.getScoreboard().getPlayerTeam(teamName);

        return level.getScoreboard().addPlayerTeam(teamName);
    }
}
