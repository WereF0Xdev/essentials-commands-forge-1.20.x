package fox.mods.essentialscommands.main;

import fox.mods.api.essentialscommands.configuration.EssentialsCommandsConfiguration;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fox.mods.essentialscommands.network.EssentialsCommandsModVariables;

public class SetHome {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).homes < EssentialsCommandsConfiguration.MAX_HOMES_NUMBER.get()) {
            {
                int _setval = (entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).homes + 1;
                entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.homes = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            entity.getPersistentData().putDouble(
                    ("home" + new java.text.DecimalFormat("##").format((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).homes) + "X"),
                    (entity.getX()));
            entity.getPersistentData().putDouble(
                    ("home" + new java.text.DecimalFormat("##").format((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).homes) + "Y"),
                    (entity.getY()));
            entity.getPersistentData().putDouble(
                    ("home" + new java.text.DecimalFormat("##").format((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).homes) + "Z"),
                    (entity.getZ()));
            entity.getPersistentData().putString(
                    ("home" + new java.text.DecimalFormat("##").format((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).homes) + "Dimension"),
                    ("" + entity.level().dimension()));
            if (EssentialsCommandsConfiguration.HOME_CREATED_MESSAGE.get().contains("%homeNumber%")) {
                if (entity instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(
                            Component.literal(("§c" + EssentialsCommandsConfiguration.HOME_CREATED_MESSAGE.get().replace("%homeNumber%",
                                    "home" + new java.text.DecimalFormat("##").format((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).homes)))),
                            true);
            } else if (!EssentialsCommandsConfiguration.HOME_CREATED_MESSAGE.get().contains("%homeNumber%")) {
                if (entity instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(Component.literal(("§c" + EssentialsCommandsConfiguration.HOME_CREATED_MESSAGE.get())), true);
            }
        } else if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).homes >= EssentialsCommandsConfiguration.MAX_HOMES_NUMBER.get()) {
            if (entity instanceof Player _player && !_player.level().isClientSide())
                _player.displayClientMessage(Component.literal(("§c" + EssentialsCommandsConfiguration.TOO_MANY_HOMES_MESSAGE.get())), true);
        }
    }
}

