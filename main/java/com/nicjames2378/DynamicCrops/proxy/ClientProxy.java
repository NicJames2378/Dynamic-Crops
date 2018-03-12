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

@Mod.EventBusSubscriber(modid=Reference.MOD_ID, value = Side.CLIENT)

public class ClientProxy extends CommonProxy {	
	@Override
	public void PreInit(FMLPreInitializationEvent event) {
		super.PreInit(event);	}
	
	@Override
	public void Init(FMLInitializationEvent event) {
		super.Init(event);
	}	
	
	@Override
	public void PostInit(FMLPostInitializationEvent event) {
		super.PostInit(event);
	}
		
	@SubscribeEvent
    public static void RegisterBlocks(RegistryEvent.Register<Block> event) {
    	ModBlocks.RegisterBlocks(event);
    }
    
	@SubscribeEvent
    public static void RegisterItems(RegistryEvent.Register<Item> event) {
    	ModBlocks.RegisterItems(event);
    }
}
