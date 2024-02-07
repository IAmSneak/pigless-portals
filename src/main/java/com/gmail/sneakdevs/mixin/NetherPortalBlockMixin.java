package com.gmail.sneakdevs.mixin;

import com.gmail.sneakdevs.PiglessPortals;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {

    @Inject(method = "randomTick", at = @At("HEAD"))
    public void piglessportals_randomTickMixin(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (world.getGameRules().getBoolean(PiglessPortals.PIGMEN_SPAWN_IN_PORTALS) && world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
            if (random.nextInt(world.getGameRules().getInt(PiglessPortals.PIGMEN_SPAWN_DELAY)) == 0) {
                while (world.getBlockState(pos).isOf(Blocks.NETHER_PORTAL)) {
                    pos = pos.down();
                }

                if (world.getBlockState(pos).allowsSpawning(world, pos, EntityType.ZOMBIFIED_PIGLIN)) {
                    Entity entity = EntityType.ZOMBIFIED_PIGLIN.spawn(world, null, null, pos.up(), SpawnReason.STRUCTURE, false, false);
                    if (entity != null) {
                        entity.resetPortalCooldown();
                    }
                }
            }
        }
    }
}


