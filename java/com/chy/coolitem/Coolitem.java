package com.chy.coolitem;

import com.chy.coolitem.block.CoolitemBlockType;
import com.chy.coolitem.blockEntity.CoolitemBlockEntityTypes;
import com.chy.coolitem.gui.CoolitemGuiHandlerType;
import com.chy.coolitem.item.CoolitemItemType;
import com.chy.coolitem.item.CoolItemGroup;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Coolitem implements ModInitializer {
	public static final String MOD_ID = "coolitem";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		CoolitemItemType.initialize();
		CoolItemGroup.initialize();
		CoolitemBlockType.initialize();
		CoolitemGuiHandlerType.initialize();
		CoolitemBlockEntityTypes.initialize();
		LOGGER.info("Hello Fabric world!");
	}
}