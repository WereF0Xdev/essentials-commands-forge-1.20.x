package fox.mods.essentialscommands.main;

import fox.mods.api.essentialscommands.configuration.EssentialsCommandsConfiguration;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import java.util.Calendar;

import fox.mods.essentialscommands.network.EssentialsCommandsModVariables;
import fox.mods.essentialscommands.EssentialsCommandsMod;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class MutePlayer {
    public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
        if (entity == null)
            return;
        if (!(entity == (new Object() {
            public Entity getEntity() {
                try {
                    return EntityArgument.getEntity(arguments, "player");
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }.getEntity()))) {
            if (((new Object() {
                public Entity getEntity() {
                    try {
                        return EntityArgument.getEntity(arguments, "player");
                    } catch (CommandSyntaxException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }.getEntity()).getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).muted == false) {
                {
                    boolean _setval = true;
                    (new Object() {
                        public Entity getEntity() {
                            try {
                                return EntityArgument.getEntity(arguments, "player");
                            } catch (CommandSyntaxException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }.getEntity()).getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.muted = _setval;
                        capability.syncPlayerVariables((new Object() {
                            public Entity getEntity() {
                                try {
                                    return EntityArgument.getEntity(arguments, "player");
                                } catch (CommandSyntaxException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            }
                        }.getEntity()));
                    });
                }
                {
                    double _setval = DoubleArgumentType.getDouble(arguments, "seconds");
                    (new Object() {
                        public Entity getEntity() {
                            try {
                                return EntityArgument.getEntity(arguments, "player");
                            } catch (CommandSyntaxException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }.getEntity()).getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.muteDuration = _setval;
                        capability.syncPlayerVariables((new Object() {
                            public Entity getEntity() {
                                try {
                                    return EntityArgument.getEntity(arguments, "player");
                                } catch (CommandSyntaxException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            }
                        }.getEntity()));
                    });
                }
                if (entity instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(Component.literal(("§aMuted " + (new Object() {
                        public Entity getEntity() {
                            try {
                                return EntityArgument.getEntity(arguments, "player");
                            } catch (CommandSyntaxException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }.getEntity()).getDisplayName().getString() + " for " + new java.text.DecimalFormat("##").format(DoubleArgumentType.getDouble(arguments, "seconds")) + "s")), true);
                if (EssentialsCommandsConfiguration.MUTE_MESSAGE.get().contains("%duration%")) {
                    if ((new Object() {
                        public Entity getEntity() {
                            try {
                                return EntityArgument.getEntity(arguments, "player");
                            } catch (CommandSyntaxException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }.getEntity()) instanceof Player _player && !_player.level().isClientSide())
                        _player.displayClientMessage(Component.literal(("§c" + EssentialsCommandsConfiguration.MUTE_MESSAGE.get().replace("%duration%", new java.text.DecimalFormat("##").format(DoubleArgumentType.getDouble(arguments, "seconds")) + "s"))), true);
                } else {
                    if ((new Object() {
                        public Entity getEntity() {
                            try {
                                return EntityArgument.getEntity(arguments, "player");
                            } catch (CommandSyntaxException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }.getEntity()) instanceof Player _player && !_player.level().isClientSide())
                        _player.displayClientMessage(Component.literal(("§c" + EssentialsCommandsConfiguration.MUTE_MESSAGE.get())), true);
                }
                EssentialsCommandsMod.LOGGER.info((entity.getDisplayName().getString() + " muted " + (new Object() {
                    public Entity getEntity() {
                        try {
                            return EntityArgument.getEntity(arguments, "player");
                        } catch (CommandSyntaxException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                }.getEntity()).getDisplayName().getString() + " for " + new java.text.DecimalFormat("##").format(DoubleArgumentType.getDouble(arguments, "seconds")) + "s at " + Calendar.getInstance().getTime().toString()));
            } else if (((new Object() {
                public Entity getEntity() {
                    try {
                        return EntityArgument.getEntity(arguments, "player");
                    } catch (CommandSyntaxException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }.getEntity()).getCapability(EssentialsCommandsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EssentialsCommandsModVariables.PlayerVariables())).muted == true) {
                if (entity instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(Component.literal(("§c" + (new Object() {
                        public Entity getEntity() {
                            try {
                                return EntityArgument.getEntity(arguments, "player");
                            } catch (CommandSyntaxException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }.getEntity()).getDisplayName().getString() + " is already muted.")), true);
            }
        } else if (entity == (new Object() {
            public Entity getEntity() {
                try {
                    return EntityArgument.getEntity(arguments, "player");
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }.getEntity())) {
            if (entity instanceof Player _player && !_player.level().isClientSide())
                _player.displayClientMessage(Component.literal("§cYou can't mute yourself!"), true);
        }
    }
}
