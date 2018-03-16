package com.nicjames2378.DynamicCrops.proxy;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.blocks.ModBlocks;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID, value = Side.SERVER)

public class CommonProxy {	
	public void PreInit(FMLPreInitializationEvent event) {
    
	}
	
	public void Init(FMLInitializationEvent event) {
	  
	}	
	
	public void PostInit(FMLPostInitializationEvent event) {
    
	}
	
	public void registerItemRenderer(Item item, int meta, String id) {
		
	}
	
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.RegisterBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	ModBlocks.RegisterBlockItems(event);
    }
}
