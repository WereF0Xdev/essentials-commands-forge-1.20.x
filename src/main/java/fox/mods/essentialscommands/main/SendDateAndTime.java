package fox.mods.essentialscommands.main;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.util.Calendar;

public class SendDateAndTime {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player _player && !_player.level().isClientSide())
            _player.displayClientMessage(Component.literal(("§a" + Calendar.getInstance().getTime().toString())), true);
    }
}

