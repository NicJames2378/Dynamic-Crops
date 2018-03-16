package com.nicjames2378.DynamicCrops.baseClasses;

import java.util.ArrayList;
import java.util.List;

import com.nicjames2378.DynamicCrops.IColorable;
import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.blocks.ModBlocks;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseSeed extends ItemSeeds implements IColorable {
	protected String name;
	protected Block cb;
	protected int color = -1;
	protected boolean isDynamic = false;
    protected static List<Item> COLORED_ITEMS = new ArrayList<>();

	public BaseSeed(Block cropBlock, Block soilBlock, String registryName, int col) {
		super(cropBlock, soilBlock);
		this.name = registryName;
		this.cb = cropBlock;
		this.color = col;
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
		setCreativeTab(Main.modCreativeTab);
	}
	
	public void registerItemModel() {		
		Main.logger.info("          Registering Item Model: item=" + name + ", ");
		if(getTextureColor() >= 0) {
			COLORED_ITEMS.add(this);
			String itemName = this.getRegistryName().getResourceDomain() + ":dseed";// + (this.isDynamic ? "dseed" : "seed");
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(itemName, "inventory"));
		} else {
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		}
	}
	
	public Block GetCropBlock() {
		return cb;
	}
	
	public BaseSeed SetIsDynamic(boolean bool) {
		this.isDynamic = bool;
		return this;
	}
	
	public BaseSeed SetColor(int color) {
		this.color = color;
		return this;
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