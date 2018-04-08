package com.nicjames2378.DynamicCrops.baseClasses;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.blocks.ModBlocks;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
	
//	private void regModel() {
//		BaseCrop c = this;
//		ModelLoader.setCustomStateMapper(c, new StateMapperBase() {
//            @Override
//            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
//                //ResourceLocation regName = new ResourceLocation("dynamiccrops:crop_nullanium");
//                //BaseSeed seed = SEEDS.getValue(regName);
//                //if (seed.getOverrides().getBlockState() != null)
//                //    return new ModelResourceLocation(seed.getOverrides().getBlockState().getPath(), "age=" + state.getValue(BlockResourcefulCrop.AGE));
//            	Main.logger.debug("Crop Register - " + c.getRegistryName());
//                return new ModelResourceLocation(ModBlocks.cropNullanium.getRegistryName(), "[age=" + c.AGE + "]");
//            }
//        });
//	}
	
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
    
//	@SubscribeEvent
//    //@SideOnly(Side.CLIENT)
//    public static void registerModels(ModelRegistryEvent event) {
//        ModelLoader.setCustomStateMapper(CROP, new StateMapperBase() {
//            @Override
//            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
//                ResourceLocation regName = new ResourceLocation(state.getValue(BlockResourcefulCrop.SEED_TYPE).replace("_", ":"));
//                Seed seed = SEEDS.getValue(regName);
//                if (seed.getOverrides().getBlockState() != null)
//                    return new ModelResourceLocation(seed.getOverrides().getBlockState().getPath(), "age=" + state.getValue(BlockResourcefulCrop.AGE));
//
//                return new ModelResourceLocation(state.getBlock().getRegistryName(), "age=" + state.getValue(BlockResourcefulCrop.AGE));
//            }
//        });
//    }
	
	public int getCropAge(IBlockState state)
    {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue();
    }
		
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
}
