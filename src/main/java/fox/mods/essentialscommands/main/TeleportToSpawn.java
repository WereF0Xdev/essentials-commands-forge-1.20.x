package fox.mods.essentialscommands.main;

import fox.mods.api.essentialscommands.configuration.EssentialsCommandsConfiguration;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import fox.mods.essentialscommands.network.EssentialsCommandsModVariables;
import fox.mods.essentialscommands.EssentialsCommandsMod;

public class TeleportToSpawn {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).spawnInCooldown == false) {
            if (EssentialsCommandsConfiguration.SPAWN_ENABLED.get() == true) {
                if (entity instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(Component.literal(("§aTeleporting in " + new java.text.DecimalFormat("##").format(EssentialsCommandsConfiguration.SPAWN_TELEPORT_TIME.get()) + "s")), true);
                {
                    boolean _setval = true;
                    entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.spawnInCooldown = _setval;
                        capability.syncPlayerVariables(entity);
                    });
                }
                EssentialsCommandsMod.queueServerWork((int) (20 * EssentialsCommandsConfiguration.SPAWN_TELEPORT_TIME.get()), () -> {
                    {
                        Entity _ent = entity;
                        _ent.teleportTo(EssentialsCommandsConfiguration.SPAWN_X.get(), EssentialsCommandsConfiguration.SPAWN_Y.get(), EssentialsCommandsConfiguration.SPAWN_Z.get());
                        if (_ent instanceof ServerPlayer _serverPlayer)
                            _serverPlayer.connection.teleport(EssentialsCommandsConfiguration.SPAWN_X.get(), EssentialsCommandsConfiguration.SPAWN_Y.get(), EssentialsCommandsConfiguration.SPAWN_X.get(), _ent.getYRot(), _ent.getXRot());
                    }
                    if (entity instanceof Player _player && !_player.level().isClientSide())
                        _player.displayClientMessage(Component.literal("§aTeleported!"), false);
                    EssentialsCommandsMod.queueServerWork((int) (20 * EssentialsCommandsConfiguration.SPAWN_COOLDOWN.get()), () -> {
                        {
                            boolean _setval = false;
                            entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                capability.spawnInCooldown = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                    });
                });
            } else if (EssentialsCommandsConfiguration.SPAWN_ENABLED.get() == false) {
                if (entity instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(Component.literal("§c" + EssentialsCommandsConfiguration.SPAWN_DISABLED_MESSAGE.get()), false);
            }
        } else if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).spawnInCooldown == true) {
            if (entity instanceof Player _player && !_player.level().isClientSide())
                _player.displayClientMessage(Component.literal("§c" + EssentialsCommandsConfiguration.COOLDOWN_MESSAGE.get()), false);
        }
    }
}

