package com.chy.coolitem.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CoolitemArmorTemplate {
    public static final RegistryEntry<ArmorMaterial> SUPER = registerMaterial("super", Map.of(ArmorItem.Type.HELMET, 0x7FFFFFFF, ArmorItem.Type.CHESTPLATE, 0x7FFFFFFF, ArmorItem.Type.LEGGINGS, 0x7FFFFFFF, ArmorItem.Type.BOOTS, 0x7FFFFFFF), 0x7FFFFFFF, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, () -> Ingredient.ofItems(CoolitemItemType.SUPER_INGOT),0x7FFFFFFF,0x7FFFFFFF,false);
    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Identifier.of("coolitem", id), "", dyeable));
        ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of("coolitem", id), material);
        return RegistryEntry.of(material);
    }
}
