package com.nicjames2378.DynamicCrops.GUI;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class UICreativeTab extends CreativeTabs {
	
	public UICreativeTab (int index, String label) {
		super(index, label);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Blocks.SOUL_SAND);
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> itemList) {
		super.displayAllRelevantItems(itemList);
	}
}
