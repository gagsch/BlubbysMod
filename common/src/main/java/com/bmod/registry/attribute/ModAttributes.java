package com.bmod.registry.attribute;

import com.bmod.BlubbysMod;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

import java.util.function.Supplier;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.ATTRIBUTE_REGISTRY);

    public static final Supplier<Attribute> DIGGING_SPEED = ATTRIBUTES.register("generic.digging_speed", () -> new RangedAttribute("attribute.name.generic.digging_speed", 1, 0, Double.MAX_VALUE).setSyncable(true));
    public static final Supplier<Attribute> SWIMMING_SPEED = ATTRIBUTES.register("generic.swimming_speed", () -> new RangedAttribute("attribute.name.generic.swimming_speed", 1, 0, Double.MAX_VALUE).setSyncable(true));
}
