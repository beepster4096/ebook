package drmeepster.ebook;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Util{
	
	public static float getXP(Tag tag){
		// Should always be true but can be modified with commands
		if(tag instanceof CompoundTag){
			CompoundTag enchantTag = (CompoundTag)tag;
			
			Enchantment enchantment = Registry.ENCHANTMENT.get(new Identifier(enchantTag.getString("id")));
			
			if(enchantment.isCursed()){
				return -1;
			}else{
				float lvl = enchantTag.getInt("lvl");
				float maxLevel = enchantment.getMaximumLevel();
				
				//magic equation
				//if lvl = 1 then 1 xp
				//if lvl = maxLevel then 3 xp
				//values in between are floored
				
				return Math.min((2 / (maxLevel - 1)) * (lvl - 1) + 1, maxLevel);
			}
		}else{
			return 0;
		}
	}
}
