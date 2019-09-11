package drmeepster.ebook;

import drmeepster.ebook.item.EBookItems;
import net.fabricmc.api.ModInitializer;

public class EBookMod implements ModInitializer{
	
	@Override
	public void onInitialize(){
		EBookItems.init();
	}
}
