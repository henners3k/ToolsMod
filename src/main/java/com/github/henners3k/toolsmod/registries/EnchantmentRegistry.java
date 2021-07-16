package com.github.henners3k.toolsmod.registries;

import com.github.henners3k.toolsmod.H3KsToolsMod;
import com.github.henners3k.toolsmod.enchatment.LumberJackEnchantment;
import com.github.henners3k.toolsmod.enchatment.VeinMiningEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EnchantmentRegistry {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, H3KsToolsMod.MOD_ID);

    public static final RegistryObject<VeinMiningEnchantment> VEIN_MINING = ENCHANTMENTS.register("vein_mining", () -> new VeinMiningEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<LumberJackEnchantment> LUMBER_JACK = ENCHANTMENTS.register("lumber_jack", () -> new LumberJackEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));

}
