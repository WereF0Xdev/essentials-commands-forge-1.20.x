package fox.mods.essentialscommands.events;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import fox.mods.essentialscommands.network.EssentialsCommandsModVariables;

@Mod.EventBusSubscriber
public class PlayerIsHit {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity());
        }
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player) {
            if ((entity.getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).godMode == true) {
                if (event != null && event.isCancelable()) {
                    event.setCanceled(true);
                } else if (event != null && event.hasResult()) {
                    event.setResult(Event.Result.DENY);
                }
                if (entity instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(Component.literal("§dYou are in God Mode!"), true);
            }
        }
    }
}

