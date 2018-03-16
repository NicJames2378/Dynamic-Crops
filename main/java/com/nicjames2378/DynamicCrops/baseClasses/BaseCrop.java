package com.nicjames2378.DynamicCrops.baseClasses;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.items.ModItems;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BaseCrop extends BlockCrops {
	public Item itemSeed;
	protected Item itemYield;
	
	public BaseCrop(Item seed, Item yield, String registryName) {
		this.itemSeed = seed;
		this.itemYield = yield;
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
		this.mycrop = seed;
	}
	public Item mycrop;
	
	@Override
	protected Item getSeed() {
		return itemSeed;
	}
	
	@Override
	protected Item getCrop() {
		return itemYield;
	}
}
