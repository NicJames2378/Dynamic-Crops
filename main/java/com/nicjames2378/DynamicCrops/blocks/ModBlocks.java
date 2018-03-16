package com.nicjames2378.DynamicCrops.blocks;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.baseClasses.BaseBlock;
import com.nicjames2378.DynamicCrops.baseClasses.BaseCrop;
import com.nicjames2378.DynamicCrops.items.ModItems;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	
    public static BaseBlock testanium = new BaseBlock(Material.ROCK, "block_testanium");    
    
    // Due to a workaround I implemented to prevent needing a separate class for each crop, you need to re-register the
    // seeds for each crop in the RegisterSeedsToCrops function below. Sorry for the inconvenience, but I don't know of
    // an alternative right now.
    public static BaseCrop cropTestanium = new BaseCrop(ModItems.seedTestanium, ModItems.ingotTestanium, "crop_testanium");
	
	public static void RegisterSeedsToCrops() {
		cropTestanium.itemSeed = ModItems.seedTestanium;
	}
    
    public static void RegisterBlocks(RegistryEvent.Register<Block> event) {   	
    	event.getRegistry().registerAll(
    		testanium,
    		cropTestanium
    	);
    }
    
    public static void RegisterBlockItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(
        	testanium.createItemBlock()
        );
    }
    
    public static void RegisterModels() {
    	testanium.registerItemModel(Item.getItemFromBlock(testanium));
    }
}
