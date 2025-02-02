package com.chy.coolitem.gui;

import com.chy.coolitem.blockEntity.DiamondCraftingTableBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.LoomScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class DiamondCraftingTableGui extends HandledScreen<DiamondCraftingTableGuiHandler> {
    private static final Identifier Gui = Identifier.of("coolitem","textures/gui/diamond_craft_gui.png");
    public DiamondCraftingTableGui(DiamondCraftingTableGuiHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight=256;
        this.backgroundWidth=256;
        this.playerInventoryTitleX=-9999;
        this.playerInventoryTitleY=-9999;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(Gui, x, y, 0, 0, 256, 256);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}