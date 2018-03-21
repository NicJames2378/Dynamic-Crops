package com.nicjames2378.DynamicCrops.baseClasses;

import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BaseCrop extends BlockCrops {
	public Item itemSeed;
	protected Item itemYield;
	protected int seedYieldCount = 1;
	protected int itemYieldCount = 1;
	//protected ItemStack[] otherItemDrops = null;
	
	public BaseCrop(Item seed, Item yield, String registryName) {
		this.itemSeed = seed;
		this.itemYield = yield;
		setUnlocalizedName(Reference.MOD_ID + "." + registryName);
		setRegistryName(registryName);
}
	
	@Override
	public Item getSeed() {
		return itemSeed;
	}
	
	@Override
	protected Item getCrop() {
		return itemYield;
	}
	
	@Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.clear();
		if (getAge(state) >= 7) {
			drops.add(new ItemStack(itemSeed, seedYieldCount));
			drops.add(new ItemStack(itemYield, itemYieldCount));
			
			/*if (otherItemDrops.length > 0 && !(otherItemDrops==null)) {
				for(ItemStack i : otherItemDrops) {
					drops.add(i);
				}
			}*/
		}
    }
	
	public BaseCrop setDrops(int seedYieldCount, int itemYieldCount) {//, ItemStack[] otherItems) {
		this.seedYieldCount = seedYieldCount;
		this.itemYieldCount = itemYieldCount;
		//otherItemDrops = otherItems;
		return this;
	}
}
