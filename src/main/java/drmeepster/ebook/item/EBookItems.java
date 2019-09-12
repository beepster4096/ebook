package drmeepster.ebook.item;

import drmeepster.ebook.Util;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EBookItems{

	public static final Item COOKBOOK = new Item(new Item.Settings()
		.group(ItemGroup.FOOD)
		.food(new FoodComponent.Builder().hunger(3).saturationModifier(0.6F).meat().build()));

	public static void init(){
		Util.register("item", "ebook:cookbook", COOKBOOK);
	}
}
