package com.nicjames2378.DynamicCrops.items;

import java.util.ArrayList;
import java.util.List;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.baseClasses.BaseItem;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	// Remember to add an item json for items!
	public static BaseItem ingotTestanium = new BaseItem("ingot_testanium");
    
	public static void Initialize() { // Add any items with no special nbt data here. They will be looped and generated automatically.
		
    }
	
	public static void RegisterItems(IForgeRegistry<Item> registry) {
		registry.registerAll(
				ingotTestanium
		);
	}
	
	public static void RegisterModels() {
		ingotTestanium.registerItemModel();
	}
}
