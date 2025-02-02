package com.chy.coolitem.item;

import net.minecraft.block.Block;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

public class SuperToolMaterial implements ToolMaterial {
    public static final SuperToolMaterial INSTANCE = new SuperToolMaterial();

    private SuperToolMaterial() {}

    @Override
    public int getDurability() {
        return 0x7FFFFFFF;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0x7FFFFFF0;
    }

    @Override
    public float getAttackDamage() {
        return 0x7FFFFFF0;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return BlockTags.AIR;
    }

    @Override
    public int getEnchantability() {
        return 0x7FFFFFF0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(CoolitemItemType.SUPER_INGOT);
    }
}
