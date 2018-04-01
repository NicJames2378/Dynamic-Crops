package com.nicjames2378.DynamicCrops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nicjames2378.DynamicCrops.baseClasses.BaseCrop;
import com.nicjames2378.DynamicCrops.baseClasses.BaseSeed;
import com.nicjames2378.DynamicCrops.config.Configurator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
		
	private static boolean usePseudo = false;
	
	//TODO: Add configuration file whitelist for what items to make crops for
	protected static Item[] pseudoWhitelist = new Item[] {
			(Items.APPLE),
			(Items.COOKIE),
			(Items.REPEATER),
			(Items.CAKE),
			(Items.DIAMOND),
			(Items.DIAMOND_HOE)
	};
	
	protected static int[] cols = new int[] {
			0xff0000,
			0x663300,
			0xcccccc,
			0xff33cc,
			0x00ccff,
			0x00ccff
	};
	
	private static List<BaseCrop> cropsList = new ArrayList<BaseCrop>();
	private static List<BaseSeed> seedsList = new ArrayList<BaseSeed>();
	
	public List<BaseSeed> getSeedsList() {
		return seedsList;
	}
	
	public List<BaseCrop> getCropsList() {
		return cropsList;
	}
	
	public static void createCropBlocks(RegistryEvent.Register<Block> event) {
		if (usePseudo) {
			for(Item i : pseudoWhitelist) {
				BaseCrop newCrop = new BaseCrop(null, i, "dcrop_" + i.getUnlocalizedName());
				cropsList.add(newCrop);
				event.getRegistry().register(newCrop);
			}	
		} else {
			for(String s : Configurator.CATEGORY_WHITELIST.whitelist) {
				Item i = Item.getByNameOrId(s);
				BaseCrop newCrop = new BaseCrop(null, i, "dcrop_" + i.getRegistryName());
				cropsList.add(newCrop);
				event.getRegistry().register(newCrop);
			}	
		}
	}	
	
	public static void createCropSeeds(RegistryEvent.Register<Item> event) {
		if (usePseudo) {
			for(int b = 0; b < cropsList.size(); b++) {
				BaseSeed newSeed = new BaseSeed(cropsList.get(b), Blocks.FARMLAND, 
						"dseed_" + pseudoWhitelist[b].getUnlocalizedName(), cols[b]); //TODO: Dynamically calculate seed color from item
				newSeed.setIsDynamic(true).setDisplayName(pseudoWhitelist[b].getItemStackDisplayName(new ItemStack(pseudoWhitelist[b])) + " Seeds");
				newSeed.setCreativeTab(Main.modCreativeTab);
				cropsList.get(b).itemSeed = newSeed;
				seedsList.add(newSeed);
				event.getRegistry().register(newSeed);
				newSeed.registerItemModel();
			}
		} else {
			Random random = new Random();
			for(int b = 0; b < cropsList.size(); b++) {
				int nextInt = random.nextInt(256*256*256);

		        Main.logger.info("DynamicPlants: " + Configurator.CATEGORY_WHITELIST.whitelist[b] + ", " + nextInt);
		        
				BaseSeed newSeed = new BaseSeed(cropsList.get(b), Blocks.FARMLAND, 
						"dseed_" + Item.getByNameOrId(Configurator.CATEGORY_WHITELIST.whitelist[b]).getRegistryName(), 0x00ccff); //TODO: Dynamically calculate seed color from item
				newSeed.setIsDynamic(true).setDisplayName(Item.getByNameOrId(Configurator.CATEGORY_WHITELIST.whitelist[b])
						.getItemStackDisplayName(new ItemStack(Item.getByNameOrId(Configurator.CATEGORY_WHITELIST.whitelist[b]))) + " Seeds");
				newSeed.setCreativeTab(Main.modCreativeTab);
				cropsList.get(b).itemSeed = newSeed;
				seedsList.add(newSeed);
				event.getRegistry().register(newSeed);
				newSeed.registerItemModel();
			}
		}
	}
}