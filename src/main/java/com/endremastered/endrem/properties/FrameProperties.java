package com.endremastered.endrem.properties;

import com.endremastered.endrem.registry.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.StringIdentifiable;

public enum FrameProperties implements StringIdentifiable {
    EMPTY,
    OLD_EYE,
    ROGUE_EYE,
    NETHER_EYE,
    COLD_EYE,
    CORRUPTED_EYE,
    MAGICAL_EYE,
    BLACK_EYE,
    LOST_EYE,
    WITHER_EYE,
    END_CRYSTAL_EYE,
    GUARDIAN_EYE,
    WITCH_EYE;

    @Override
    public String asString() {
        return this.asItemSerializedName();
    }

    public String asItemSerializedName() {
        switch (this) {
            case EMPTY: default:
                return "empty";
            case OLD_EYE:
                return "old_eye";
            case ROGUE_EYE:
                return "rogue_eye";
            case NETHER_EYE:
                return "nether_eye";
            case COLD_EYE:
                return "cold_eye";
            case CORRUPTED_EYE:
                return "corrupted_eye";
            case MAGICAL_EYE:
                return "magical_eye";
            case BLACK_EYE:
                return "black_eye";
            case LOST_EYE:
                return "lost_eye";
            case WITHER_EYE:
                return "wither_eye";
            case END_CRYSTAL_EYE:
                return "end_crystal_eye";
            case GUARDIAN_EYE:
                return "guardian_eye";
            case WITCH_EYE:
                return "witch_eye";
        }
    }

    public static FrameProperties getFrameProperty(Item eye) {
        FrameProperties frameProperties = FrameProperties.EMPTY;

        if (eye == ItemRegistry.OLD_EYE.asItem()) {
            frameProperties = FrameProperties.OLD_EYE;
        } else if (eye == ItemRegistry.ROGUE_EYE.asItem()) {
            frameProperties = FrameProperties.ROGUE_EYE;
        } else if (eye == ItemRegistry.NETHER_EYE.asItem()) {
            frameProperties = FrameProperties.NETHER_EYE;
        } else if (eye == ItemRegistry.COLD_EYE.asItem()) {
            frameProperties = FrameProperties.COLD_EYE;
        } else if (eye == ItemRegistry.CORRUPTED_EYE.asItem()) {
            frameProperties = FrameProperties.CORRUPTED_EYE;
        } else if (eye == ItemRegistry.MAGICAL_EYE.asItem()) {
            frameProperties = FrameProperties.MAGICAL_EYE;
        } else if (eye == ItemRegistry.BLACK_EYE.asItem()) {
            frameProperties = FrameProperties.BLACK_EYE;
        } else if (eye == ItemRegistry.LOST_EYE.asItem()) {
            frameProperties = FrameProperties.LOST_EYE;
        } else if (eye == ItemRegistry.WITHER_EYE.asItem()) {
            frameProperties = FrameProperties.WITHER_EYE;
        } else if (eye == ItemRegistry.END_CRYSTAL_EYE.asItem()) {
            frameProperties = FrameProperties.END_CRYSTAL_EYE;
        } else if (eye == ItemRegistry.GUARDIAN_EYE.asItem()) {
            frameProperties = FrameProperties.GUARDIAN_EYE;
        } else if (eye == ItemRegistry.WITCH_EYE.asItem()) {
            frameProperties = FrameProperties.WITCH_EYE;
        }

        return frameProperties;
    }

}
