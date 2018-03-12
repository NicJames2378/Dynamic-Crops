package com.nicjames2378.DynamicCrops.GUI;

import net.minecraft.creativetab.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class UICreativeTab extends CreativeTabs {
	public UICreativeTab (int index, String label) {
		super(index, label);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Blocks.SOUL_SAND);
	}
}
