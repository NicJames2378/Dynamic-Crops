package com.nicjames2378.DynamicCrops.blocks;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.baseClasses.BaseBlock;
import com.nicjames2378.DynamicCrops.baseClasses.BaseCrop;
import com.nicjames2378.DynamicCrops.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks {
	
    public static BaseBlock blockNullanium = new BaseBlock(Material.ROCK, "block_nullanium");    
    
    // Due to a workaround I implemented to prevent needing a separate class for each crop, you need to re-register the
    // seeds for each crop in the RegisterSeedsToCrops function below. Sorry for the inconvenience, but I don't know of
    // an alternative right now. (NOTE: Only applies to manually created ones)
    public static BaseCrop cropNullanium = new BaseCrop(new ItemStack(ModItems.seedNullanium, 1), 
    		new ItemStack[] {
    				new ItemStack(ModItems.ingotNullanium, 1),
    				new ItemStack(Blocks.DIRT, 1)
    		}, "crop_nullanium");
	
	public static void RegisterSeedsToCrops() {
		cropNullanium.setSeed(new ItemStack(ModItems.seedNullanium, 1));
	}
    
    public static void RegisterBlocks(RegistryEvent.Register<Block> event) {   	
    	event.getRegistry().registerAll(
    		blockNullanium,
    		cropNullanium
    	);
    	
    	// Register all blocks to the creative tab here
    	blockNullanium.setCreativeTab(Main.modCreativeTab);
    }
    
    public static void RegisterBlockItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(
        	blockNullanium.createItemBlock()
        );
    }
    
    public static void RegisterModels() {
    	blockNullanium.registerItemModel(Item.getItemFromBlock(blockNullanium));
    }
}
