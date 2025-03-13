package com.chy.coolitem.blockEntity;

import com.chy.coolitem.gui.CopierGuiHandler;
import com.chy.coolitem.gui.DiamondCraftingTableGuiHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class CopierBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, Inventory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int ticks;
    public CopierBlockEntity(BlockPos pos, BlockState state) {
        super(CoolitemBlockEntityTypes.COPIER, pos, state);
    }
    @Override
    public int getMaxCountPerStack() {
        return 1;
    }
    @Override
    public Text getDisplayName() {
        return Text.empty();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.getStack(0).isEmpty();
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

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        CopierGuiHandler handler = new CopierGuiHandler(syncId, playerInventory, (Inventory) this);
        return handler;
    }

    @Override
    public void clear() {
        this.items.clear();
    }
    public static void tick(World world, BlockPos pos, BlockState state, CopierBlockEntity blockEntity) {
        blockEntity.tick(world,pos,state);
    }
    public void tick(World world, BlockPos pos, BlockState state) {
        this.getStack(0).capCount(1);
        if(this.ticks==20){
            this.ticks = 0;
            if(!this.getStack(0).isEmpty()){
                ItemScatterer.spawn(world,pos.getX(),pos.getY(),pos.getZ(),this.getStack(0).copy());
            }
        }
        ++this.ticks;
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.items, registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.items, registryLookup);
    }

    public DefaultedList<ItemStack> getItems() {
        return this.items;
    }
}
