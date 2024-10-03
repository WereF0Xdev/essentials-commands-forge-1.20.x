package fox.mods.essentialscommands.main;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

public class SetGamemodeSurvival {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof ServerPlayer _player)
            _player.setGameMode(GameType.SURVIVAL);
        if (entity instanceof Player _player && !_player.level().isClientSide())
            _player.displayClientMessage(Component.literal("§aGamemode set to Survival"), true);
    }
}

