package com.nicjames2378.DynamicCrops.baseClasses;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BaseBlock extends Block {
	protected String name;
	
	public BaseBlock(Material material, String registryName, float hardness, float resistance ) {
		super(material);
		this.name = registryName;
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
		setCreativeTab(Main.modCreativeTab);
		setHardness(hardness);
		setResistance(resistance);
	}
	
	public BaseBlock(Material material, String registryName ) {
		super(material);
		this.name = registryName;
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
		setCreativeTab(Main.modCreativeTab);
		setHardness(3f);
		setResistance(5f);
	}
	
	public void registerItemModel(Item itemBlock) {
		Main.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
	
	@Override
	public BaseBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}