package com.nicjames2378.DynamicCrops.items;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.baseClasses.BaseItem;
import com.nicjames2378.DynamicCrops.baseClasses.BaseSeed;
import com.nicjames2378.DynamicCrops.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	// Remember to add an item json for items!
	public static BaseItem ingotNullanium = new BaseItem("ingot_nullanium");
	
	// All seeds must be registered in the method below to their crops and all crops to their seeds in ModBlocks.RegisterSeedsToCrops!!!!
	// Forge is not made to natively make the crops at runtime, but it is a requirement to do what we want, so this is our workaround.
	// Do NOT uncomment the code after the seed line. It is only there to reference how NOT to do it (in this mod anyways!)
	public static BaseSeed seedNullanium;// = new BaseSeed(ModBlocks.cropNullanium, Blocks.FARMLAND, "seed_nullanium");
	
	public static void RegisterCropSeeds() {
		seedNullanium = new BaseSeed(ModBlocks.cropNullanium, Blocks.FARMLAND, "seed_nullanium", -1);
	}
	
	public static void RegisterItems(IForgeRegistry<Item> registry) {
		RegisterCropSeeds();
		ModBlocks.RegisterSeedsToCrops();
		registry.registerAll(
				ingotNullanium,
				seedNullanium
		);
		
		ingotNullanium.setCreativeTab(Main.modCreativeTab);
		seedNullanium.setCreativeTab(Main.modCreativeTab);		
	}
	
	public static void RegisterModels() {
		ingotNullanium.registerItemModel();
		seedNullanium.registerItemModel();
	}
}
