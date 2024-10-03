package fox.mods.essentialscommands.main;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class Extinguish {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        entity.clearFire();
        if (entity instanceof Player _player && !_player.level().isClientSide())
            _player.displayClientMessage(Component.literal("§aDo you smell something burning?"), true);
    }
}

