package com.endremastered.endrem.items;

import com.endremastered.endrem.registry.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

    public class EndCrystalArmorMaterial implements ArmorMaterial {
        private static final int helmet = 3;
        private static final int chestplate = 8;
        private static final int leggings = 6;
        private static final int boots = 3;

        private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
        private static final int[] PROTECTION_VALUES = new int[]{helmet, leggings, chestplate, boots};

        @Override
        public int getDurability(EquipmentSlot slot) {
            return BASE_DURABILITY[slot.getEntitySlotId()] * 33;
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
            return Ingredient.ofItems(ItemRegistry.END_CRYSTAL_FRAGMENT); //Change to End Crystal Ingot
        }

        @Override
        public String getName() {
            return "end_crystal";
        }

        @Override
        public float getToughness() {
            return 2.0F;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1F;
        }
    }

