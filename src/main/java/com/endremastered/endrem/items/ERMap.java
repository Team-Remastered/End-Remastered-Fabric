package com.endremastered.endrem.items;

import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.registry.RegisterHandler;
import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructure;
import com.endremastered.endrem.world.structures.ERJigsawStructures;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ERMap {
    interface StructureGetter {
        StructureFeature<?> get();
    }
    private static final StructureGetter STRUCTURE_TO_LOCATE = () -> StructureLocator.getStructureToLocate(ERConfig.MAP_LOCATES_STRUCTURE);

    private static int getMinPrice() {
        return Integer.parseInt(ERConfig.MAP_TRADE_VALUES.getList().get(0));
    }

    private static int getMaxPrice() {
        return Integer.parseInt(ERConfig.MAP_TRADE_VALUES.getList().get(1));
    }

    private static int getEXP() {
        return Integer.parseInt(ERConfig.MAP_TRADE_VALUES.getList().get(2));
    }

    public static ItemStack createMap(ServerWorld serverWorld, BlockPos playerPosition) {

        // Get position of marker
        BlockPos structurePos = RegisterHandler.MAP_ML.getNearestPosition(serverWorld, playerPosition);

        // Create map
        ItemStack stack = FilledMapItem.createMap(serverWorld, structurePos.getX(), structurePos.getZ(), (byte) 2, true, true);
        FilledMapItem.fillExplorationMap(serverWorld, stack);
        MapState.addDecorationsNbt(stack, structurePos, "+", MapIcon.Type.TARGET_X);

        stack.setCustomName(Text.of("End Remastered Map"));
        return stack;
    }

    private static class CustomMapTrade implements TradeOffers.Factory {

        @Override
        public TradeOffer create(@NotNull Entity entity, Random random){
            int priceEmeralds = random.nextInt(getMaxPrice() - getMinPrice() + 1) + getMinPrice();
            if (!entity.world.isClient) {
                ItemStack map = createMap( (ServerWorld) entity.world, entity.getBlockPos());
                return new TradeOffer(new ItemStack(Items.EMERALD, priceEmeralds), new ItemStack(Items.COMPASS), map, 12, getEXP(), 0.2F);
            }
            return null;
        }
    }

    public static void registerVillagerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 3, factories -> {
            factories.add(new CustomMapTrade());
        });

        TradeOfferHelper.registerWanderingTraderOffers(0,factories -> {
            factories.add(new CustomMapTrade());
        });
    }
}
