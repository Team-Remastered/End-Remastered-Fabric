package com.endremastered.endrem.items;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.registry.ERItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

public class EndCrystalTools {

    public final static ToolMaterial MATERIAL = new EndCrystalToolMaterial();

    public static class EndCrystalHoe extends HoeItem {
        public EndCrystalHoe(){
            super(MATERIAL, -4,0.0F, (new FabricItemSettings().group(EndRemastered.ENDREM_TAB)));
        }
    }

    public static class EndCrystalPickaxe extends PickaxeItem {
        public EndCrystalPickaxe(){
            super(MATERIAL, 1, -2.8F, (new FabricItemSettings().group(EndRemastered.ENDREM_TAB)));
        }
    }

    public static class EndCrystalAxe extends AxeItem {
        public EndCrystalAxe(){
            super(MATERIAL, 5.0F, -3.0F, (new FabricItemSettings().group(EndRemastered.ENDREM_TAB)));
        }
    }

    public static class EndCrystalSword extends SwordItem {
        public EndCrystalSword(){
            super(MATERIAL, 3, -2.4F, (new FabricItemSettings().group(EndRemastered.ENDREM_TAB)));
        }
    }

    public static class EndCrystalShovel extends ShovelItem {
        public EndCrystalShovel(){
            super(MATERIAL, 1.5F, -3.0F, (new FabricItemSettings().group(EndRemastered.ENDREM_TAB)));
        }
    }

    public static class EndCrystalToolMaterial implements ToolMaterial {

        @Override
        public int getDurability() {
            return ERConfig.getData().END_CRYSTAL_GEAR.TOOLS.durability;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return ERConfig.getData().END_CRYSTAL_GEAR.TOOLS.speed;
        }

        @Override
        public float getAttackDamage() {
            return ERConfig.getData().END_CRYSTAL_GEAR.TOOLS.damageBonus;
        }

        @Override
        public int getMiningLevel() {
            return 4;
        }

        @Override
        public int getEnchantability() {
            return 10;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ERItems.END_CRYSTAL_FRAGMENT);
        }
    }
}
