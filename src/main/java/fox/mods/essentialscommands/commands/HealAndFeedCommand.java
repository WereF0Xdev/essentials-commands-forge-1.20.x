package fox.mods.essentialscommands.commands;

import fox.mods.essentialscommands.main.StrikeThunder;
import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import fox.mods.essentialscommands.main.HealAndFeed;

@Mod.EventBusSubscriber
public class HealAndFeedCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("heal").requires(s -> s.hasPermission(4)).executes(arguments -> {
            Level world = arguments.getSource().getUnsidedLevel();
            double x = arguments.getSource().getPosition().x();
            double y = arguments.getSource().getPosition().y();
            double z = arguments.getSource().getPosition().z();
            Entity entity = arguments.getSource().getEntity();
            if (entity == null && world instanceof ServerLevel _servLevel)
                entity = FakePlayerFactory.getMinecraft(_servLevel);
            Direction direction = Direction.DOWN;
            if (entity != null)
                direction = entity.getDirection();

            HealAndFeed.execute(entity);
            return 0;
        }).then(
                event.getDispatcher().register(Commands.literal("feed")
                                .requires(s -> s.hasPermission(4))
                                .executes(arguments -> {
                                    Level world = arguments.getSource().getUnsidedLevel();
                                    double x = arguments.getSource().getPosition().x();
                                    double y = arguments.getSource().getPosition().y();
                                    double z = arguments.getSource().getPosition().z();
                                    Entity entity = arguments.getSource().getEntity();
                                    if (entity == null && world instanceof ServerLevel _servLevel)
                                        entity = FakePlayerFactory.getMinecraft(_servLevel);
                                    Direction direction = Direction.DOWN;
                                    if (entity != null)
                                        direction = entity.getDirection();

                                    HealAndFeed.execute(entity);
                                    return 0;
                                })
                )
        ));
    }
}

