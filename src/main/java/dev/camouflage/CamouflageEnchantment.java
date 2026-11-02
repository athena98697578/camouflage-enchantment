package dev.camouflage;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public final class CamouflageEnchantment {
    private static final Identifier ENCHANTMENT_ID =
            Identifier.fromNamespaceAndPath("camouflage_enchantment", "camouflage");

    private CamouflageEnchantment() {
    }

    public static boolean isWearingCamouflageHelmet(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemEnchantments enchantments = helmet.get(DataComponents.ENCHANTMENTS);

        if (enchantments == null || enchantments.isEmpty()) {
            return false;
        }

        for (Holder<Enchantment> enchantment : enchantments.keySet()) {
            ResourceKey<Enchantment> key = enchantment.unwrapKey().orElse(null);
            if (key != null && key.identifier().equals(ENCHANTMENT_ID)) {
                return true;
            }
        }

        return false;
    }
}
