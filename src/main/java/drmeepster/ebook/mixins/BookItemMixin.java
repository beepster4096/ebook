package drmeepster.ebook.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.BookItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

@Mixin(BookItem.class)
public abstract class BookItemMixin extends Item{
	
	private BookItemMixin(Settings item$Settings_1){
		super(item$Settings_1);
	}

	private static final FoodComponent FOOD = (new FoodComponent.Builder()).hunger(1).saturationModifier(0).build();
	
	@Override
	public boolean isFood() {
		return true;
	}

	@Override
	public FoodComponent getFoodComponent() {
		return FOOD;
	}
}
