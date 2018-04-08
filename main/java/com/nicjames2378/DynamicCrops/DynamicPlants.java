package com.nicjames2378.DynamicCrops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nicjames2378.DynamicCrops.baseClasses.BaseCrop;
import com.nicjames2378.DynamicCrops.baseClasses.BaseSeed;
import com.nicjames2378.DynamicCrops.config.Configurator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
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
	 * cropTest
	 * 
	 * Create seedTest (blank)
	 * ModItems Register Items
	 * RegisterCropSeeds -> assign values to seedTest
	 * ModBlocks RegisterSeedsToCrops -> assign seed to cropTest
	 * register seedTest
	 * 
	 * ModItems Register Models
	 * seedTest registerItemModel
	 */
	
	private static List<BaseCrop> cropsList = new ArrayList<BaseCrop>();
	private static List<BaseSeed> seedsList = new ArrayList<BaseSeed>();

	public static List<BaseSeed> getSeedsList() {
		return seedsList;
	}

	public static List<BaseCrop> getCropsList() {
		return cropsList;
	}

	public static void createCropBlocks(RegistryEvent.Register<Block> event) {
		for (String s : Configurator.CATEGORY_WHITELIST.whitelist) {
			Item item = Item.getByNameOrId(s);
			BaseCrop newCrop = new BaseCrop(null, new ItemStack(item, 1), "dcrop_" + item.getRegistryName());
			cropsList.add(newCrop);
			event.getRegistry().register(newCrop);
		}
	}

	public static void createCropSeeds(RegistryEvent.Register<Item> event) {
		for (int b = 0; b < cropsList.size(); b++) {
			Item item = Item.getByNameOrId(Configurator.CATEGORY_WHITELIST.whitelist[b]);
			BaseSeed newSeed = new BaseSeed(cropsList.get(b), Blocks.FARMLAND,
					"dseed_" + item.getRegistryName(), -1); //Main.proxy.getStackColor(new ItemStack(item)));
			newSeed.setIsDynamic(true).setDisplayName(item.getItemStackDisplayName(new ItemStack(item)));// + " Seeds");
			newSeed.setCreativeTab(Main.modCreativeTab);
			cropsList.get(b).setSeed(new ItemStack(newSeed, 1));
			seedsList.add(newSeed);
			event.getRegistry().register(newSeed);
			Main.logger.debug("DynamicPlants: Registering Model");
			newSeed.registerItemModel();
		}
	}
	
	private static int getItemColor() {
		Random random = new Random();
		return random.nextInt(256 * 256 * 256);// TODO: Dynamically calculate seed color from item
	}
}