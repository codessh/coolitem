package com.chy.coolitem.blockEntity;

import com.chy.coolitem.gui.DiamondCraftingTableGuiHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Optional;


public class DiamondCraftingTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, Inventory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(82, ItemStack.EMPTY);

    public DefaultedList<ItemStack> getItems() {
        return items;
    }
    public DiamondCraftingTableBlockEntity(BlockPos pos, BlockState state) {
        super(CoolitemBlockEntityTypes.DIAMOND_CRAFTING_TABLE, pos, state);
    }
    @Override
    public Text getDisplayName() {
        return Text.empty();
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : items){
            if(!itemstack.isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack itemStack=Inventories.splitStack(this.items, slot, amount);
        if(!itemStack.isEmpty()){
            this.markDirty();
        }
        return itemStack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.items, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.items.set(slot, stack);
        stack.capCount(this.getMaxCount(stack));
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, DiamondCraftingTableBlockEntity entity) {
        entity.tick(world, blockPos, state);
    }

    public void tick(World world, BlockPos blockPos, BlockState state) {
        this.updateRecipes();
    }
    public void updateRecipes(){
        World world=this.world;
        RecipeManager recipeManager = world.getRecipeManager();
        ArrayList<ItemStack> itemsArray=new ArrayList<>();
        for (int i = 0; i < 81; ++i) {
            itemsArray.add(this.getStack(i));
        }
        Optional<RecipeEntry<CraftingRecipe>> match = recipeManager.getFirstMatch(RecipeType.CRAFTING, CraftingRecipeInput.create(9, 9, itemsArray), world);
        this.setStack(81, DiamondCraftingTableBlockEntity.getResult((match.map(RecipeEntry::value).orElse(null)), itemsArray));
        }
    private static ItemStack getResult(CraftingRecipe recipe, ArrayList<ItemStack> itemsArray){
        if (recipe instanceof ShapedRecipe shapedRecipe) {
            return shapedRecipe.craft(CraftingRecipeInput.create(9,9,itemsArray),null);
        } else if (recipe instanceof ShapelessRecipe shapelessRecipe) {
            return shapelessRecipe.craft(CraftingRecipeInput.create(9,9,itemsArray),null);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
          DiamondCraftingTableGuiHandler handler = new DiamondCraftingTableGuiHandler(syncId, playerInventory, (Inventory) this);
          return handler;
    }
}
