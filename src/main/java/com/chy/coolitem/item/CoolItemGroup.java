package com.chy.coolitem.item;

import com.chy.coolitem.block.CoolitemBlockType;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class CoolItemGroup {
    public static final ItemGroup COOLITEM_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of("coolitem", "coolitem_group"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(CoolitemItemType.NETHERITE_DIAMOND))
                    .displayName(Text.translatable("itemGroup.coolitem.coolitem_group"))
            .entries((context, entries) -> {
                entries.add(CoolitemBlockType.DIAMOND_CRAFTING_TABLE);
                entries.add(CoolitemItemType.NETHERITE_DIAMOND);
                entries.add(CoolitemBlockType.NETHERITE_DIAMOND_BLOCK);
                entries.add(CoolitemBlockType.SUPER_QUARTZ_BLOCK);
                entries.add(CoolitemBlockType.SUPER_AMETHYST_BLOCK);
                entries.add(CoolitemBlockType.SUPER_COAL_BLOCK);
                entries.add(CoolitemBlockType.SUPER_LAPIS_BLOCK);
                entries.add(CoolitemBlockType.SUPER_COPPER_BLOCK);
                entries.add(CoolitemBlockType.SUPER_IRON_BLOCK);
                entries.add(CoolitemBlockType.SUPER_REDSTONE_BLOCK);
                entries.add(CoolitemBlockType.SUPER_GOLD_BLOCK);
                entries.add(CoolitemBlockType.SUPER_EMERALD_BLOCK);
                entries.add(CoolitemBlockType.SUPER_DIAMOND_BLOCK);
                entries.add(CoolitemBlockType.SUPER_NETHERITE_BLOCK);
                entries.add(CoolitemItemType.ORE_CRYSTAL);
                entries.add(CoolitemItemType.SUPER_INGOT);
                entries.add(CoolitemBlockType.SUPER_BLOCK);
                entries.add(CoolitemItemType.SUPER_SHOVEL);
                entries.add(CoolitemItemType.SUPER_PICKAXE);
                entries.add(CoolitemItemType.SUPER_AXE);
                entries.add(CoolitemItemType.SUPER_HOE);
                entries.add(CoolitemItemType.SUPER_SWORD);
                entries.add(CoolitemItemType.SUPER_HELMET);
                entries.add(CoolitemItemType.SUPER_CHESTPLATE);
                entries.add(CoolitemItemType.SUPER_LEGGINGS);
                entries.add(CoolitemItemType.SUPER_BOOTS);
            })
            .build());

    public static void initialize() {}
}