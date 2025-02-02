package com.chy.coolitem.block;

import com.chy.coolitem.blockEntity.DiamondCraftingTableBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class CoolitemBlockType {
    public static final Block NETHERITE_DIAMOND_BLOCK = register("netherite_diamond_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHERITE).strength(50.0f, 1200.0f)));
    public static final Block DIAMOND_CRAFTING_TABLE = register("diamond_crafting_table", new DiamondCraftingTableBlock(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.DIAMOND_BLUE).sounds(BlockSoundGroup.METAL).strength(5.0f, 6.0f)));
    public static final Block SUPER_IRON_BLOCK = register("super_iron_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.IRON_GRAY).sounds(BlockSoundGroup.METAL).strength(5.0f, 6.0f)));
    public static final Block SUPER_AMETHYST_BLOCK = register("super_amethyst_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(1.5f, 1.5f)));
    public static final Block SUPER_COAL_BLOCK = register("super_coal_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.STONE).strength(5.0f, 6.0f)));
    public static final Block SUPER_COPPER_BLOCK = register("super_copper_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.METAL).strength(3.0f, 6.0f)));
    public static final Block SUPER_LAPIS_BLOCK = register("super_lapis_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.LAPIS_BLUE).sounds(BlockSoundGroup.STONE).strength(3.0f, 3.0f)));
    public static final Block SUPER_GOLD_BLOCK = register("super_gold_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.GOLD).sounds(BlockSoundGroup.METAL).strength(3.0f, 6.0f)));
    public static final Block SUPER_REDSTONE_BLOCK = register("super_redstone_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.BRIGHT_RED).sounds(BlockSoundGroup.METAL).strength(5.0f, 6.0f)));
    public static final Block SUPER_EMERALD_BLOCK = register("super_emerald_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.EMERALD_GREEN).sounds(BlockSoundGroup.METAL).strength(5.0f, 6.0f)));
    public static final Block SUPER_DIAMOND_BLOCK = register("super_diamond_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.DIAMOND_BLUE).sounds(BlockSoundGroup.METAL).strength(5.0f, 6.0f)));
    public static final Block SUPER_QUARTZ_BLOCK = register("super_quartz_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.OFF_WHITE).sounds(BlockSoundGroup.STONE).strength(0.8f, 0.8f)));
    public static final Block SUPER_NETHERITE_BLOCK = register("super_netherite_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHERITE).strength(50.0f, 1200.0f)));
    public static final Block SUPER_BLOCK = register("super_block", new Block(AbstractBlock.Settings.create().requiresTool().mapColor(MapColor.TERRACOTTA_BROWN).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(50.0f, 1200.0f)));

    public static <T extends Block> T register(String path, T block) {
        Registry.register(Registries.BLOCK, Identifier.of("coolitem", path), block);
        Registry.register(Registries.ITEM, Identifier.of("coolitem", path), new BlockItem(block, new Item.Settings()));
        return block;
    }

    public static void initialize() {}
}
