package com.nicjames2378.DynamicCrops;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.nicjames2378.DynamicCrops.Block.BlockTestanium;
import com.nicjames2378.DynamicCrops.utils.NameRegistry;
import com.nicjames2378.DynamicCrops.utils.Reference;

public class ModBlocks{

	public static BlockTestanium blockTestanium;
	
	public static void init() {
		
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, NameRegistry.TESTANIUM);
		blockTestanium = new BlockTestanium();
		blockTestanium.setRegistryName(location);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher) {
		
	}
	
}
