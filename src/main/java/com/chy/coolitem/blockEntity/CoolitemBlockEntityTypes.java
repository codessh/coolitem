package com.chy.coolitem.blockEntity;

import com.chy.coolitem.block.CoolitemBlockType;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class CoolitemBlockEntityTypes {
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of("coolitem", path), blockEntityType);
    }
    public static final BlockEntityType<DiamondCraftingTableBlockEntity> DIAMOND_CRAFTING_TABLE = register(
            "diamond_crafting_table",
            BlockEntityType.Builder.create(DiamondCraftingTableBlockEntity::new, CoolitemBlockType.DIAMOND_CRAFTING_TABLE).build()
    );
    public static final BlockEntityType<CopierBlockEntity> COPIER = register(
            "copier",
            BlockEntityType.Builder.create(CopierBlockEntity::new, CoolitemBlockType.COPIER).build()
    );
    public static void initialize() {}
}
