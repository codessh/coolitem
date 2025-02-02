package com.chy.coolitem;

import com.chy.coolitem.gui.CoolitemGuiHandlerType;
import com.chy.coolitem.gui.DiamondCraftingTableGui;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CoolitemClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(CoolitemGuiHandlerType.DIAMOND_CRAFTING_TABLE_GUI_HANDLER, DiamondCraftingTableGui::new);
	}
}