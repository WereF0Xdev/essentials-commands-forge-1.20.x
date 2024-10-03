package fox.mods.essentialscommands.main;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fox.mods.essentialscommands.network.EssentialsCommandsModVariables;

public class ToggleFlight {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).flightEnabled == false) {
            {
                boolean _setval = true;
                entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.flightEnabled = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            if (entity instanceof Player _player) {
                _player.getAbilities().mayfly = true;
                _player.onUpdateAbilities();
            }
            if (entity instanceof Player _player && !_player.level().isClientSide())
                _player.displayClientMessage(Component.literal("§dTo the stars!"), true);
        } else if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).flightEnabled == true) {
            {
                boolean _setval = false;
                entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.flightEnabled = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            if (entity instanceof Player _player) {
                _player.getAbilities().mayfly = false;
                _player.getAbilities().flying = false;
                _player.onUpdateAbilities();
            }
            if (entity instanceof Player _player && !_player.level().isClientSide())
                _player.displayClientMessage(Component.literal("§dBack to the ground!"), true);
        }
    }
}

