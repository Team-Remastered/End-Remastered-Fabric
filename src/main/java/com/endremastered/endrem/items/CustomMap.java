package com.endremastered.endrem.items;

import com.endremastered.endrem.config.ConfigHandler;
import com.endremastered.endrem.util.StructureLocator;
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

import java.util.Random;

public class CustomMap {
    interface StructureGetter {
        StructureFeature<?> get();
    }
    private static final StructureGetter STRUCTURE_TO_LOCATE = () -> StructureLocator.getStructureToLocate(ConfigHandler.MAP_LOCATES_STRUCTURE);

    private static final int minPrice = 10;
    private static final int maxPrice = 20;
    private static final int experienceGiven = 10;

    public static ItemStack createMap(World world, BlockPos position) {
        if(!(world instanceof ServerWorld))
            return ItemStack.EMPTY;

        ServerWorld serverWorld = (ServerWorld) world;

        BlockPos structurePos = serverWorld.getChunkManager().getChunkGenerator().locateStructure(serverWorld, STRUCTURE_TO_LOCATE.get(), position, 100, false);

        if(structurePos == null)
            return ItemStack.EMPTY;

        ItemStack stack = FilledMapItem.createMap(world, structurePos.getX(), structurePos.getZ(), (byte) 2, true, true);
        // fillExplorationMap
        FilledMapItem.fillExplorationMap((ServerWorld) world, stack);
        MapState.addDecorationsNbt(stack, structurePos, "+", MapIcon.Type.TARGET_X);
        if (STRUCTURE_TO_LOCATE.get() == ERJigsawStructures.END_GATE){
            stack.setCustomName(new TranslatableText("item.endrem.end_gate_map"));
        } else if (STRUCTURE_TO_LOCATE.get() == ERConfiguredStructure.END_CASTLE) {
            stack.setCustomName(new TranslatableText("item.endrem.end_castle_map"));
        } else {
            stack.setCustomName(Text.of("Structure Map"));
        }
        return stack;
    }

    public static void addTradeToVillager() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 3, factories -> {
            factories.add(new CustomMapTrade());
        });

        TradeOfferHelper.registerWanderingTraderOffers(0,factories -> {
            factories.add(new CustomMapTrade());
        });
    }

    private static class CustomMapTrade implements TradeOffers.Factory {

        @Override
        public TradeOffer create(Entity entity, Random random){
            int priceEmeralds = random.nextInt(maxPrice - minPrice + 1) + minPrice;
            ItemStack map = createMap(entity.world, entity.getBlockPos());

            if (map != ItemStack.EMPTY) {
                return new TradeOffer(new ItemStack(Items.EMERALD, priceEmeralds), new ItemStack(Items.COMPASS), map, 12, experienceGiven, 0.2F);
            }
            return null;
        }
    }
}
