package fox.mods.essentialscommands.main;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class HealAndFeed {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player _player)
            _player.getFoodData().setFoodLevel(100);
        if (entity instanceof Player _player)
            _player.getFoodData().setSaturation(100);
        if (entity instanceof LivingEntity _entity)
            _entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
        if (entity instanceof Player _player && !_player.level().isClientSide())
            _player.displayClientMessage(Component.literal("§aHealed and fed"), true);
    }
}

