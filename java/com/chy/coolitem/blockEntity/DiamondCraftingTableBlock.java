package com.chy.coolitem.blockEntity;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Vector;


public class DiamondCraftingTableBlock extends BlockWithEntity {
    public DiamondCraftingTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends DiamondCraftingTableBlock> getCodec() {
        return createCodec(DiamondCraftingTableBlock::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DiamondCraftingTableBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(world.isClient) {
            return ActionResult.SUCCESS;
        }else{
            BlockEntity blockEntity=world.getBlockEntity(pos);
            if(blockEntity instanceof DiamondCraftingTableBlockEntity entity && player instanceof ServerPlayerEntity serverPlayer){
                player.openHandledScreen(entity);
            }
            return ActionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient?null:validateTicker(type, CoolitemBlockEntityTypes.DIAMOND_CRAFTING_TABLE, DiamondCraftingTableBlockEntity::tick);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DiamondCraftingTableBlockEntity blockEntity = (DiamondCraftingTableBlockEntity) world.getBlockEntity(pos);
        DefaultedList<ItemStack> itemsArray = DefaultedList.of();
        itemsArray.addAll(blockEntity.getItems().subList(0,81));
        ItemScatterer.spawn(world, pos, itemsArray);
        return super.onBreak(world, pos, state, player);
    }
}