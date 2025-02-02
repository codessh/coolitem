package com.chy.coolitem.gui;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import static java.lang.Math.min;


public class DiamondCraftingTableGuiHandler extends ScreenHandler {
    private final Inventory inventory;
    public final World world;
    public final PlayerEntity player;
    private final PlayerInventory playerInventory;
    public DiamondCraftingTableGuiHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(82));
    }
    public DiamondCraftingTableGuiHandler(int syncId, PlayerInventory playerInventory, Inventory inventory){
        super(CoolitemGuiHandlerType.DIAMOND_CRAFTING_TABLE_GUI_HANDLER, syncId);
        this.playerInventory=playerInventory;
        this.inventory=inventory;
        this.player=playerInventory.player;
        this.world=this.player.getWorld();

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, i*9+j, 12+j*18, 8+i*18));
            }
        }
        this.addSlot(new DiamondCraftOutputSlot(inventory, 81, 210, 80, this));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j+i*9+9, 39+j*18, 174+i*18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 39+i*18, 232));
        }
    }


    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            returnStack = originalStack.copy();

            if (index == 81) {
                int minn=0, i;
                boolean f=false;
                for(i = 0; i < 81; ++i){
                    if(!this.getSlot(i).getStack().isEmpty()){
                        if(f){
                            minn=min(minn, this.getSlot(i).getStack().getCount());
                        }else{
                            minn=this.getSlot(i).getStack().getCount();
                            f=true;
                        }
                    }
                }
                if(!f){
                    return ItemStack.EMPTY;
                }
                for (i = 0; i < 81; ++i) {
                    this.getSlot(i).takeStack(minn);
                }
                for(i = 0; i < minn; ++i) {
                    this.mergeItemStack(returnStack.copy(), 82, 117);
                }
                return ItemStack.EMPTY;
            } else if (index >= 82 && index < 118) {
                if (this.mergeItemStack(originalStack, 0, 80)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 0 && index < 81) {
                if (this.mergeItemStack(originalStack, 82, 117)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.mergeItemStack(originalStack, 0, 80)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (originalStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, originalStack);
        }

        return returnStack;
    }

    public boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex){

        for(int i=startIndex;i<=endIndex;++i){
            if(ItemStack.areItemsAndComponentsEqual(stack,this.getSlot(i).getStack())){
                int count=min(stack.getCount(), this.getSlot(i).getMaxItemCount(stack) - this.getSlot(i).getStack().getCount());
                this.getSlot(i).setStack(stack.copyWithCount(this.getSlot(i).getStack().getCount()+count));
                stack.setCount(stack.getCount()-count);
                if(stack.getCount()==0){
                    return false;
                }
            }
        }
        for(int i=startIndex;i<=endIndex;++i){
            if (this.getSlot(i).getStack().isEmpty()) {
                int count=min(stack.getCount(), this.getSlot(i).getMaxItemCount(stack));
                this.getSlot(i).setStack(stack.copyWithCount(count));
                stack.setCount(stack.getCount()-count);
                if(stack.getCount()==0){
                    return false;
                }
            }
        }
        this.player.dropStack(stack);
        return true;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    @Override
    public boolean canInsertIntoSlot(Slot slot) {
        return slot.id != 81;
    }
}