package com.chy.coolitem.gui;


import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;


public class CoolitemGuiHandlerType {
    public static final ScreenHandlerType<DiamondCraftingTableGuiHandler> DIAMOND_CRAFTING_TABLE_GUI_HANDLER=register("coolitem:diamond_craft_gui", new ScreenHandlerType<>(new ScreenHandlerType.Factory<>() {
        @Override
        public DiamondCraftingTableGuiHandler create(int syncId, PlayerInventory playerInventory) {
            return new DiamondCraftingTableGuiHandler(syncId, playerInventory);
        }
    }, FeatureSet.empty()));
    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, factory);
    }
    public static void initialize() {}
}
