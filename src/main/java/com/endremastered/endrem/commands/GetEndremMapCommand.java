package com.endremastered.endrem.commands;

import com.endremastered.endrem.items.ERMap;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

public class GetEndremMapCommand {
    public GetEndremMapCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("get_endrem_map")
                .requires((source) -> source.hasPermissionLevel(2))// Requires Cheat Enabled
                .executes((command) -> getEndremMap(command.getSource())));
    }

    private int getEndremMap(ServerCommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayer();
        player.giveItemStack(ERMap.createMap(player.getServerWorld(), player.getBlockPos()));
        source.sendFeedback(new TranslatableText(String.format("Gave [Endrem Map] x 1 to %s", player.getDisplayName().getString())), true);
        return 1;
    }
}
