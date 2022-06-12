package com.teamremastered.endrem.mixin.accessor;

import net.minecraft.entity.EyeOfEnderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EyeOfEnderEntity.class)
public interface EyeOfEnderEntityAccessorMixin {
    @Accessor("dropsItem")
    public void setDropsItem(boolean dropsItem);

}
