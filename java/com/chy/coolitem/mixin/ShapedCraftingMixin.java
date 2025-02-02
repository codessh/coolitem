package com.chy.coolitem.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.util.dynamic.Codecs;
import org.spongepowered.asm.mixin.*;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


@Mixin(RawShapedRecipe.Data.class)
public class ShapedCraftingMixin{


    @Mutable
    @Final
    @Shadow
    private static Codec<List<String>> PATTERN_CODEC = Codec.STRING.listOf().comapFlatMap((pattern) -> {
        if (pattern.isEmpty()) {
            return DataResult.error(() -> "Invalid pattern: empty pattern not allowed");
        } else {
            int i = ((String)pattern.get(0)).length();
            Iterator<String> var2 = pattern.iterator();
            String string;
            do {
                if (!var2.hasNext()) {
                    return DataResult.success(pattern);
                }

                string = var2.next();
            } while(i == string.length());

            return DataResult.error(() -> "Invalid pattern: each row must be the same width");
        }
    }, Function.identity());

    @Mutable
    @Final
    @Shadow
    private static Codec<Character> KEY_ENTRY_CODEC = Codec.STRING.comapFlatMap((keyEntry) -> {
        if (keyEntry.length() != 1) {
            return DataResult.error(() -> "Invalid key entry: '" + keyEntry + "' is an invalid symbol (must be 1 character only).");
        } else {
            return " ".equals(keyEntry) ? DataResult.error(() -> "Invalid key entry: ' ' is a reserved symbol.") : DataResult.success(keyEntry.charAt(0));
        }
    }, String::valueOf);;

    @Mutable
    @Final
    @Shadow
    public static MapCodec<RawShapedRecipe.Data> CODEC = RecordCodecBuilder.<RawShapedRecipe.Data>mapCodec(instance -> instance.group(
            Codecs.strictUnboundedMap(KEY_ENTRY_CODEC, Ingredient.DISALLOW_EMPTY_CODEC).fieldOf("key").forGetter(RawShapedRecipe.Data::key),
            PATTERN_CODEC.fieldOf("pattern").forGetter(RawShapedRecipe.Data::pattern)
    ).apply(instance, RawShapedRecipe.Data::new));
}
