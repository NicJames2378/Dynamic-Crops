package com.nicjames2378.DynamicCrops.proxy;

import com.nicjames2378.DynamicCrops.ModBlocks; //Blocks Init
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher; //To declare variable ItemModelMesher mesher
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID, value = Side.CLIENT)

public class ClientProxy extends CommonProxy {	
	@Override
	public void PreInit(FMLPreInitializationEvent event) {
		super.PreInit(event);	
	}
	
	@Override
	public void Init(FMLInitializationEvent event) {
		super.Init(event);
		
		//Declaring this variable so I don't have a massive chunk of text every time I want to use it
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		
		//Blocks init
		ModBlocks.initClient(mesher);
	}	
	
	@Override
	public void PostInit(FMLPostInitializationEvent event) {
		super.PostInit(event);
	}
}
