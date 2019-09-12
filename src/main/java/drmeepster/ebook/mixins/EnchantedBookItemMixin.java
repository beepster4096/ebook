package drmeepster.ebook.mixins;

import org.spongepowered.asm.mixin.Mixin;

import drmeepster.ebook.Util;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.World;

@Mixin(EnchantedBookItem.class)
public abstract class EnchantedBookItemMixin extends Item{

	private EnchantedBookItemMixin(Settings item$Settings_1){
		super(item$Settings_1);
	}

	private static final FoodComponent FOOD = 
		new FoodComponent.Builder().hunger(2).saturationModifier(0).alwaysEdible().build();

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
		int xp = 1;

		for(Tag tag : EnchantedBookItem.getEnchantmentTag(item)){
			xp += Util.getXP(tag);
		}

		if(entity instanceof PlayerEntity){
			((PlayerEntity) entity).addExperience(Math.max(xp, 0));
		}

		return super.finishUsing(item, world, entity);
	}
}
