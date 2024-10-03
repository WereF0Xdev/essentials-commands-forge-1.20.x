package fox.mods.essentialscommands.events;

import fox.mods.api.essentialscommands.configuration.EssentialsCommandsConfiguration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import fox.mods.essentialscommands.network.EssentialsCommandsModVariables;
import fox.mods.essentialscommands.EssentialsCommandsMod;

@Mod.EventBusSubscriber
public class PlayerJoinsTheGame {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        execute(event, event.getEntity().level(), event.getEntity());
    }

    public static void execute(LevelAccessor world, Entity entity) {
        execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).spawnInCooldown == true) {
            EssentialsCommandsMod.queueServerWork((int) (20 * EssentialsCommandsConfiguration.SPAWN_COOLDOWN.get()), () -> {
                {
                    boolean _setval = false;
                    entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.spawnInCooldown = _setval;
                        capability.syncPlayerVariables(entity);
                    });
                }
            });
        }
    }
}

