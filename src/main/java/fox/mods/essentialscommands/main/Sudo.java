package fox.mods.essentialscommands.main;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class Sudo {
    public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
        if (entity == null)
            return;
        {
            Entity _ent = (new Object() {
                public Entity getEntity() {
                    try {
                        return EntityArgument.getEntity(arguments, "player");
                    } catch (CommandSyntaxException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }.getEntity());
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                        _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), (new Object() {
                    public String getMessage() {
                        try {
                            return MessageArgument.getMessage(arguments, "command").getString();
                        } catch (CommandSyntaxException ignored) {
                            return "";
                        }
                    }
                }).getMessage());
            }
        }
        if (entity instanceof Player _player && !_player.level().isClientSide())
            _player.displayClientMessage(Component.literal(("§aForced " + (new Object() {
                public Entity getEntity() {
                    try {
                        return EntityArgument.getEntity(arguments, "player");
                    } catch (CommandSyntaxException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }.getEntity()).getDisplayName().getString() + " to execute " + (new Object() {
                public String getMessage() {
                    try {
                        return MessageArgument.getMessage(arguments, "command").getString();
                    } catch (CommandSyntaxException ignored) {
                        return "";
                    }
                }
            }).getMessage())), true);
    }
}

