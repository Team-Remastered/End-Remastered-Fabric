package com.endremastered.endrem.items;

import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.registry.ERItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

    public class EndCrystalArmor implements ArmorMaterial {
        private static final int helmet = 3;
        private static final int chestplate = 8;
        private static final int leggings = 6;
        private static final int boots = 3;

        private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
        private static final int[] PROTECTION_VALUES = new int[]{helmet, leggings, chestplate, boots};

        @Override
        public int getDurability(EquipmentSlot slot) {
            return (int) (BASE_DURABILITY[slot.getEntitySlotId()] * ERConfig.getData().END_CRYSTAL_GEAR.ARMOR.durabilityFactor);
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return PROTECTION_VALUES[slot.getEntitySlotId()];
        }

        @Override
        public int getEnchantability() {
            return 15;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ERItems.END_CRYSTAL_FRAGMENT); //Change to End Crystal Ingot
        }

        @Override
        public String getName() {
            return "end_crystal";
        }

        @Override
        public float getToughness() {
            return ERConfig.getData().END_CRYSTAL_GEAR.ARMOR.toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return ERConfig.getData().END_CRYSTAL_GEAR.ARMOR.knockbackResistance;
        }
    }

