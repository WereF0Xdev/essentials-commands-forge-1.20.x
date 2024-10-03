package fox.mods.essentialscommands.commands;

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

import fox.mods.essentialscommands.main.SetDaytime;

@Mod.EventBusSubscriber
public class SetDaytimeCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("day")
                        .requires(s -> s.hasPermission(4))
                        .executes(arguments -> {
                            Level world = arguments.getSource().getLevel();
                            Entity entity = arguments.getSource().getEntity();
                            if (entity == null && world instanceof ServerLevel _servLevel) {
                                entity = FakePlayerFactory.getMinecraft(_servLevel);
                            }

                            SetDaytime.execute(world, entity);
                            return 1;
                        })
                        .then(
                                Commands.literal("sun")
                                        .requires(s -> s.hasPermission(4))
                                        .executes(arguments -> {
                                            Level world = arguments.getSource().getLevel();
                                            Entity entity = arguments.getSource().getEntity();
                                            if (entity == null && world instanceof ServerLevel _servLevel) {
                                                entity = FakePlayerFactory.getMinecraft(_servLevel);
                                            }

                                            SetDaytime.execute(world, entity);
                                            return 1;
                                        })
                        )
        );
    }
}
