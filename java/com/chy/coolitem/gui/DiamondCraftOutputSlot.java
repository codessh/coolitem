package com.chy.coolitem.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.collection.DefaultedList;

import static java.lang.Math.min;


public class DiamondCraftOutputSlot extends Slot {
    private final DiamondCraftingTableGuiHandler handler;

    public DiamondCraftOutputSlot(Inventory inventory, int index, int x, int y, DiamondCraftingTableGuiHandler handler) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    protected void onCrafted(ItemStack stack) {
        if (this.getStack().getCount() > 0) {
            stack.onCraftByPlayer(handler.world, handler.player, this.getStack().getCount());
        }

        this.setStack(ItemStack.EMPTY);
    }

    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        this.onCrafted(stack);
        for (int i = 0; i < 81; ++i) {
            this.handler.getSlot(i).takeStack(1);
        }
    }

}
