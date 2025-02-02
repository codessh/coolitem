package com.chy.coolitem.mixin;


import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.*;

@Mixin(ShapelessRecipe.Serializer.class)
public class ShapelessCraftingMixin {
    @Shadow
    @Final
    private static MapCodec<ShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codec.STRING.optionalFieldOf("group", "").forGetter(ShapelessRecipe::getGroup),
            CraftingRecipeCategory.CODEC.fieldOf("category").orElse(CraftingRecipeCategory.MISC).forGetter(ShapelessRecipe::getCategory),
            ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(shapelessRecipe -> shapelessRecipe.getResult(null)),
            Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").flatXmap((ingredients) -> {
        Ingredient[] ingredients2 = ingredients.stream().filter((ingredient) -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
        if (ingredients2.length == 0) {
            return DataResult.error(() -> "No ingredients for shapeless recipe");
        } else {
            return DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredients2));
        }
    }, DataResult::success).forGetter(ShapelessRecipe::getIngredients)).apply(instance, ShapelessRecipe::new));
}
