package com.nicjames2378.DynamicCrops.Block;

import com.nicjames2378.DynamicCrops.utils.NameRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;;

public class BlockTestanium extends Block {
	
	public BlockTestanium() {
		super(Material.ROCK);
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + NameRegistry.RESOURCE_PREFIX + NameRegistry.TESTANIUM;
	}
	
}
