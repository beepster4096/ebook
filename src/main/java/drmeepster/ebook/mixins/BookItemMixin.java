package drmeepster.ebook.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BookItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(BookItem.class)
public abstract class BookItemMixin extends Item{

	private BookItemMixin(Settings item$Settings_1){
		super(item$Settings_1);
	}

	private static final FoodComponent FOOD = (new FoodComponent.Builder()).hunger(1).saturationModifier(0).alwaysEdible().build();

	@Override
	public boolean isFood(){
		return true;
	}

	@Override
	public FoodComponent getFoodComponent(){
		return FOOD;
	}
	
	@Override
	public ItemStack finishUsing(ItemStack item, World world, LivingEntity entity){
		if(entity instanceof PlayerEntity){
			((PlayerEntity)entity).addExperience(1);
		}
		
		return super.finishUsing(item, world, entity);
	}
}
