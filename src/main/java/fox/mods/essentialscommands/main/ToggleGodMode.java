package fox.mods.essentialscommands.main;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.util.Calendar;

import fox.mods.essentialscommands.network.EssentialsCommandsModVariables;
import fox.mods.essentialscommands.EssentialsCommandsMod;

public class ToggleGodMode {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).godMode == false) {
            {
                boolean _setval = true;
                entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.godMode = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            if (entity instanceof Player _player && !_player.level().isClientSide())
                _player.displayClientMessage(Component.literal("§dGod Mode enabled!"), true);
            EssentialsCommandsMod.LOGGER.info((entity.getDisplayName().getString() + " (" + entity.getStringUUID() + ") enabled God Mode at " + Calendar.getInstance().getTime().toString()));
        } else if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).godMode == true) {
            {
                boolean _setval = false;
                entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.godMode = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            if (entity instanceof Player _player && !_player.level().isClientSide())
                _player.displayClientMessage(Component.literal("§dGod Mode disabled!"), true);
            EssentialsCommandsMod.LOGGER.info((entity.getDisplayName().getString() + " (" + entity.getStringUUID() + ") disabled God Mode at " + Calendar.getInstance().getTime().toString()));
        }
    }
}

