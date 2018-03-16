package com.nicjames2378.DynamicCrops.baseClasses;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block {
	public BaseBlock(Material material, String unlocalizedName, String registryName ) {
		super(material);
		setUnlocalizedName(Reference.MOD_ID + "." + unlocalizedName);
		setRegistryName(registryName);
		setCreativeTab(Main.modCreativeTab);
	}
	
	public BaseBlock(Material material, String registryName ) {
		super(material);
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
		setCreativeTab(Main.modCreativeTab);
	}
}