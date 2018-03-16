package com.nicjames2378.DynamicCrops.blocks;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.baseClasses.BaseBlock;
import com.nicjames2378.DynamicCrops.utils.Reference;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	
    protected static List<BaseBlock> blockList = new ArrayList<BaseBlock> ();
	
    public static BaseBlock testanium = new BaseBlock(Material.ROCK, "block_testanium");
    public static BaseBlock randomium = new BaseBlock(Material.ROCK, "randomium");
    public static BaseBlock noideasium = new BaseBlock(Material.ROCK, "noideasium");
    public static BaseBlock anotherblock = new BaseBlock(Material.ROCK, "anotherblock");
    public static BaseBlock yetanotheroneium = new BaseBlock(Material.ROCK, "yetanotheroneium");
	
    public static void Initialize() { // Add any blocks with no special tile data here. They will be looped and generated automatically.
    	/*
    	 * Whereas I thought this approach was superior at first, it resulted in needing the
    	 * exact string in order to reference the block. Therefore, I changed to the above
    	 * implementation. I'm leaving this comment here to let others know why I changed things up.
    	 * 
    	blockList.add(new BaseBlock(Material.ROCK, "testanium"));
    	blockList.add(new BaseBlock(Material.ROCK, "randomium"));
    	blockList.add(new BaseBlock(Material.ROCK, "noideasium"));
    	blockList.add(new BaseBlock(Material.ROCK, "anotherblock"));
    	blockList.add(new BaseBlock(Material.ROCK, "yetanotheroneium"));
    	 *
    	 * However, I will still be using the blockList to simplify the registry code.
    	 * While this may look the same, it differs in that it changes from a string
    	 * based lookup to one more friendly to the codebase. AKA, instead of remembering
    	 * all names explicitly, you can just call the variable up top.
    	*/
    	blockList.add(testanium);
    	blockList.add(randomium);
    	blockList.add(noideasium);
    	blockList.add(anotherblock);
    	blockList.add(yetanotheroneium);
    }
    
    public static void RegisterBlocks(RegistryEvent.Register<Block> event) {   	
    	for (BaseBlock mb : ModBlocks.blockList) {
    		event.getRegistry().register(mb);
    	}
    }
      
    public static void RegisterBlockItems(RegistryEvent.Register<Item> event) {
        for (BaseBlock mb : blockList) {
    		//event.getRegistry().register(new ItemBlock(mb).setRegistryName(mb.getUnlocalizedName()));
        	event.getRegistry().register(mb.createItemBlock());
    	}
    }
    
    public static void RegisterModels() {
    	testanium.registerItemModel(Item.getItemFromBlock(testanium));
    }
}
