package com.nicjames2378.DynamicCrops.baseClasses;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.blocks.ModBlocks;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class BaseSeed extends ItemSeeds {
	protected String name;
	protected Block cb;
	
	public BaseSeed(Block cropBlock, Block soilBlock, String registryName) {
		super(cropBlock, soilBlock);
		this.name = registryName;
		this.cb = cropBlock;
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
		setCreativeTab(Main.modCreativeTab);
	}

	public void registerItemModel() {
		Main.proxy.registerItemRenderer(this, 0, name);	
	}
	
	public Block GetCropBlock() {
		return cb;
	}
}