package drmeepster.ebook.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

@Mixin(EnchantedBookItem.class)
public abstract class EnchantedBookItemMixin extends Item{

	private EnchantedBookItemMixin(Settings item$Settings_1){
		super(item$Settings_1);
	}

	private static final FoodComponent FOOD = (new FoodComponent.Builder()).hunger(2).saturationModifier(0).alwaysEdible().build();

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
			if(tag instanceof CompoundTag){
				CompoundTag enchantTag = (CompoundTag)tag;
				
				Enchantment enchantment = Registry.ENCHANTMENT.get(new Identifier(enchantTag.getString("id")));
				
				if(enchantment.isCursed()){
					xp -= 1;
				}else{
					float lvl = enchantTag.getInt("lvl");
					float maxLevel = enchantment.getMaximumLevel();
					
					//magic equation
					//if lvl = 1 then 1 xp
					//if lvl = maxLevel then 3 xp
					//values in between are floored
					
					xp += (int)Math.min((2 / (maxLevel - 1)) * (lvl - 1) + 1, maxLevel);
				}
			}
		}
		
		if(entity instanceof PlayerEntity){
			((PlayerEntity)entity).addExperience(xp);
		}
		
		return super.finishUsing(item, world, entity);
	}
}
