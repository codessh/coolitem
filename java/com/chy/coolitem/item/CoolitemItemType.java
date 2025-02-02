package com.chy.coolitem.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public final class CoolitemItemType {
    private CoolitemItemType() {}
    public static final Item NETHERITE_DIAMOND = register("netherite_diamond", new Item(new Item.Settings()));
    public static final Item ORE_CRYSTAL = register("ore_crystal", new Item(new Item.Settings()));
    public static final Item SUPER_INGOT = register("super_ingot", new Item(new Item.Settings()));
    public static final Item SUPER_SWORD = register( "super_sword",new SwordItem(SuperToolMaterial.INSTANCE, (new Item.Settings()).attributeModifiers(SwordItem.createAttributeModifiers(SuperToolMaterial.INSTANCE, 0x7FFFFFF0, 0x7FFFFFF0))));
    public static final Item SUPER_AXE = register( "super_axe",new AxeItem(SuperToolMaterial.INSTANCE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(SuperToolMaterial.INSTANCE, 0x7FFFFFF0, 0x7FFFFFF0))));
    public static final Item SUPER_PICKAXE = register( "super_pickaxe",new PickaxeItem(SuperToolMaterial.INSTANCE, new Item.Settings()));
    public static final Item SUPER_HOE = register( "super_hoe",new HoeItem(SuperToolMaterial.INSTANCE, new Item.Settings()));
    public static final Item SUPER_SHOVEL = register( "super_shovel",new ShovelItem(SuperToolMaterial.INSTANCE, new Item.Settings()));
    public static final Item SUPER_HELMET = register("super_helmet",new ArmorItem(CoolitemArmorTemplate.SUPER, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(0x7FFFFFFF)));
    public static final Item SUPER_CHESTPLATE = register("super_chestplate",new ArmorItem(CoolitemArmorTemplate.SUPER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(0x7FFFFFFF)));
    public static final Item SUPER_LEGGINGS = register("super_leggings",new ArmorItem(CoolitemArmorTemplate.SUPER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(0x7FFFFFFF)));
    public static final Item SUPER_BOOTS = register("super_boots",new ArmorItem(CoolitemArmorTemplate.SUPER, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(0x7FFFFFFF)));

    public static <T extends Item> T register(String path, T item) {
        return Registry.register(Registries.ITEM, Identifier.of("coolitem", path), item);
    }

    public static void initialize() {}
}
