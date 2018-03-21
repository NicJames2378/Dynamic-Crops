package com.nicjames2378.DynamicCrops.baseClasses;

import java.util.ArrayList;
import java.util.List;

import com.nicjames2378.DynamicCrops.IColorable;
import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseSeed extends ItemSeeds implements IColorable {
	private String name;
	private String displayName;
	private Block cb;
	private int color = -1;
	private boolean isDynamic = false;
	private static List<Item> COLORED_ITEMS = new ArrayList<>();

	public BaseSeed(Block cropBlock, Block soilBlock, String registryName, int col) {
		super(cropBlock, soilBlock);
		this.name = registryName;
		this.cb = cropBlock;
		this.color = col;
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
	}
	
	public void registerItemModel() {
		if(isDynamic) {
			COLORED_ITEMS.add(this);
			String itemName = this.getRegistryName().getResourceDomain() + ":dseed";// + (this.isDynamic ? "dseed" : "seed");
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(itemName, "inventory"));
		} else {
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		}
	}
	
	public Block getCropBlock() {
		return cb;
	}
	
	@Override // Overridden to allow chaining
	public BaseSeed setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	public BaseSeed setIsDynamic(boolean bool) {
		this.isDynamic = bool;
		return this;
	}
	
	public BaseSeed setColor(int color) {
		this.color = color;
		return this;
	}
	
	public BaseSeed setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	@Override // Overridden to allow manually setting names for seeds
    public String getItemStackDisplayName(ItemStack stack) {
        if (isDynamic && !(displayName == null || displayName == "")) {
        	return displayName;
        }
        return super.getItemStackDisplayName(stack);
    }

	@Override
	public int getTextureColor() {
		return color;
	}

	 @SideOnly(Side.CLIENT)
	public static void regColors() {
		 //Register item colors
	     Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? ((IColorable)stack.getItem()).getTextureColor() : -1, COLORED_ITEMS.toArray(new Item[]{}));
	}
}