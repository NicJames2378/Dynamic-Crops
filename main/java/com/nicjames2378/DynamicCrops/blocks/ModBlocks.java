package com.nicjames2378.DynamicCrops.blocks;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.utils.Reference;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

final class ModdedBlock extends Block {
	ModdedBlock(Material material, String unlocalizedName, String registryName ) {
		super(material);
		setUnlocalizedName(Reference.MOD_ID + "." + unlocalizedName);
		setRegistryName(registryName);
		setCreativeTab(Main.modCreativeTab);
	}
	
	ModdedBlock(Material material, String registryName ) {
		super(material);
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
		setCreativeTab(Main.modCreativeTab);
	}
}

public class ModBlocks {	
    private static List<ModdedBlock> blockList = new ArrayList<ModdedBlock> ();
    
    public static void Initialize() { // Add any blocks with no special tile data here. They will be looped and generated automatically.
    	blockList.add(new ModdedBlock(Material.ROCK, "testanium"));
    	blockList.add(new ModdedBlock(Material.ROCK, "randomium"));
    	blockList.add(new ModdedBlock(Material.ROCK, "noideasium"));
    	blockList.add(new ModdedBlock(Material.ROCK, "anotherblock"));
    	blockList.add(new ModdedBlock(Material.ROCK, "yetanotheroneium"));
    }
    
    public static void RegisterBlocks(RegistryEvent.Register<Block> event) {
    	Main.logger.info("REGISTERING BLOCKS");
    	
    	for (ModdedBlock mb : blockList) {
    		event.getRegistry().register(mb);
    	}    	
    }
    
    public static void RegisterItems(RegistryEvent.Register<Item> event) {
    	Main.logger.info("REGISTERING ITEMS");
        for (ModdedBlock mb : blockList) {
    		event.getRegistry().register(new ItemBlock(mb).setRegistryName(mb.getUnlocalizedName()));
    	}
    }
}
