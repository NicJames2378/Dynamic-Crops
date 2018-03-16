package com.nicjames2378.DynamicCrops;

import java.util.ArrayList;
import java.util.List;

import com.nicjames2378.DynamicCrops.baseClasses.BaseCrop;
import com.nicjames2378.DynamicCrops.baseClasses.BaseItem;
import com.nicjames2378.DynamicCrops.baseClasses.BaseSeed;
import com.nicjames2378.DynamicCrops.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class DynamicPlants {
	/*
	 * Order of functionality needed:
	 * 
	 * Read config whitelist of items to make crops for
	 * 
	 * Create cropTest (no seed)
	 * ModBlocks Register Blocks
	 * 		cropTest
	 * 
	 * Create seedTest (blank)
	 * ModItems Register Items
	 * 		RegisterCropSeeds -> assign values to seedTest
	 * 		ModBlocks RegisterSeedsToCrops -> assign seed to cropTest
	 * 		register seedTest
	 * 
	 * ModItems Register Models
	 * 		seedTest registerItemModel
	 */
	
	protected static Item[] pseudoWhitelist = new Item[] {
			(Items.APPLE),
			(Items.COOKIE),
			(Items.REPEATER)
	};
	
	protected static List<BaseCrop> cropsList = new ArrayList<BaseCrop>();
	protected static List<BaseSeed> seedsList = new ArrayList<BaseSeed>();
	
	public static void CreateCropBlocks(RegistryEvent.Register<Block> event) {
		for(Item i : pseudoWhitelist) {
			BaseCrop newCrop = new BaseCrop(null, i, "dcrop_" + i.getUnlocalizedName());
			cropsList.add(newCrop);
			event.getRegistry().register(newCrop);
		}		
	}

	public static void CreateCropSeeds(RegistryEvent.Register<Item> event) {
		Main.logger.info("\n\n\n\n");
		for(int b = 0; b < cropsList.size(); b++) {
			BaseSeed newSeed = new BaseSeed(cropsList.get(b), Blocks.FARMLAND, "dseed_" + pseudoWhitelist[b].getUnlocalizedName());
			cropsList.get(b).itemSeed = newSeed;
			seedsList.add(newSeed);
			event.getRegistry().register(newSeed);
			
			Main.logger.info("Crop " + cropsList.get(b).getUnlocalizedName() + " has seed " + cropsList.get(b).getSeed());
			Main.logger.info("Seed " + seedsList.get(b).getUnlocalizedName() + " has crop " + seedsList.get(b).GetCropBlock() +"\n");
			
			newSeed.registerItemModel();
		}
	}
}
