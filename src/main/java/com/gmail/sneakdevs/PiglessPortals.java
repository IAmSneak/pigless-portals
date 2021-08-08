package com.gmail.sneakdevs;

import net.minecraft.world.GameRules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;

public class PiglessPortals implements ModInitializer {

    public static GameRules.Key<GameRules.IntRule> PIGMEN_SPAWN_DELAY = GameRuleRegistry.register(
            "pigmenSpawnDelay",
            GameRules.Category.SPAWNING,
            GameRuleFactory.createIntRule(2000,1, 800000)
    );

    public static GameRules.Key<GameRules.BooleanRule> PIGMEN_SPAWN_IN_PORTALS = GameRuleRegistry.register(
            "pigmenSpawnInPortals",
            GameRules.Category.SPAWNING,
            GameRuleFactory.createBooleanRule(false)
    );

    @Override
    public void onInitialize() {
    }
}