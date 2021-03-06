package com.nicjames2378.DynamicCrops.baseClasses;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BaseCrop extends BlockCrops {
	protected ItemStack itemSeed;
	protected ItemStack[] itemYield;
	public boolean isDynamic = false;
	
	public BaseCrop(ItemStack seed, ItemStack[] yield, String registryName) {
		this.itemSeed = seed;
		this.itemYield = yield;
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
	}
	
	public BaseCrop(ItemStack seed, ItemStack yield, String registryName) {
		this.itemSeed = seed;
		this.itemYield = new ItemStack[] { 
			yield 
		};
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
	}
	
	@Override
	public Item getSeed() {
		return itemSeed.getItem();
	}
	
	public BaseCrop setSeed(ItemStack seed) {
		itemSeed = seed;
		return this;
	}
	
	// First item in array will always be the required item
	public ItemStack[] getYield() {
		return itemYield;
	}
	
	public BaseCrop setYield(ItemStack[] items) {
		if (items.length <= 0) {
			Main.logger.error("SY: Tried setting crop yield to nothing! Changes not applied.");
		} else {
			itemYield = items;
		}
		return this;
	}
	
	@Override 	// First item in array will always be the required item
	public Item getCrop() {
		return itemYield[0].getItem();
	}
	
//	Need to figure out how to dynamically texture crops.....
//	
//	public void registerCropModel() {
//		Main.logger.debug("RCM: " + isDynamic + " - " + this.getRegistryName() + ", " + this.getUnlocalizedName());
//		if(isDynamic) {
//			COLORED_ITEMS.add(this);
//			// Tells the item to get it's texture from 'dynamiccrops:dseed' instead of, for example, 'dseed_minecraft:record_blocks'
//			String itemName = Reference.MOD_ID + ":dseed";// + (this.isDynamic ? "dseed" : "seed");
//			BlockStateMapper mapper = new BlockStateMapper()
//			ModelLoader.setCustomStateMapper(this, mapper);
//			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(itemName, "inventory"));
//		} else {
//			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//		}
//	}
		
	@Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.clear();
		if (getAge(state) >= getMaxAge()) {			
			drops.add(itemSeed);

			for (ItemStack i : itemYield) {
				drops.add(i);
			}
		}
    }
//	
//	public BaseCrop setDrops(int seedYieldCount, int itemYieldCount) {//, ItemStack[] otherItems) {
//		this.seedYieldCount = seedYieldCount;
//		this.itemYieldCount = itemYieldCount;
//		return this;
//	}
}
