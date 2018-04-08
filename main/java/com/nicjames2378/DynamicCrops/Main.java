package com.nicjames2378.DynamicCrops;

import org.apache.logging.log4j.Logger;

import com.nicjames2378.DynamicCrops.GUI.UICreativeTab;
import com.nicjames2378.DynamicCrops.baseClasses.BaseCrop;
import com.nicjames2378.DynamicCrops.baseClasses.BaseSeed;
import com.nicjames2378.DynamicCrops.blocks.ModBlocks;
import com.nicjames2378.DynamicCrops.items.ModItems;
import com.nicjames2378.DynamicCrops.proxy.CommonProxy;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class Main {

	@Instance
	public static Main instance;	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;	
	public static Logger logger;	
	public static CreativeTabs modCreativeTab = new UICreativeTab(CreativeTabs.getNextID(), "creativetab");
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("DynamicCrops, ready for action!");
		proxy.PreInit(event);
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		proxy.Init(event);
		BaseSeed.regColors();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		proxy.PostInit(event);
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void RegisterBlocks(RegistryEvent.Register<Block> event) {
	    	Main.logger.debug(("REGISTRATION HANDLER: Registering Blocks").toUpperCase());	    	
	    	ModBlocks.RegisterBlocks(event);
	    	
	    	Main.logger.debug(("REGISTRATION HANDLER: Creating Dynamic Crops").toUpperCase());
	    	DynamicPlants.createCropBlocks(event);
	    }
	    
		@SubscribeEvent
	    public static void RegisterItems(RegistryEvent.Register<Item> event) {
	    	Main.logger.debug(("REGISTRATION HANDLER: Registering Items").toUpperCase());
	        ModBlocks.RegisterBlockItems(event);
	        ModItems.RegisterItems(event.getRegistry());
	        
	        Main.logger.debug(("REGISTRATION HANDLER: Creating Dynamic Seeds").toUpperCase());
	        DynamicPlants.createCropSeeds(event);
	    }
		
		@SubscribeEvent
		public static void RegisterItems(ModelRegistryEvent event) {
	        Main.logger.debug(("REGISTRATION HANDLER: Registering Models").toUpperCase());
			ModBlocks.RegisterModels();
			ModItems.RegisterModels();
			
			/*for (BaseCrop c : DynamicPlants.getCropsList()) {				
				ModelLoader.setCustomStateMapper(c, new StateMapperBase() {
		            @Override
		            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		                //ResourceLocation regName = new ResourceLocation("dynamiccrops:crop_nullanium");
		                //BaseSeed seed = SEEDS.getValue(regName);
		                //if (seed.getOverrides().getBlockState() != null)
		                //    return new ModelResourceLocation(seed.getOverrides().getBlockState().getPath(), "age=" + state.getValue(BlockResourcefulCrop.AGE));

		                return new ModelResourceLocation(ModBlocks.cropNullanium.getRegistryName(), "[age=" + c.AGE + "]");
		            }
		        });
			}*/
		}
		
		@SubscribeEvent
	    @SideOnly(Side.CLIENT)
	    public static void registerModels(ModelRegistryEvent event) {
			int buffer = new String("dcrop_").length();
			for (BaseCrop c : DynamicPlants.getCropsList()) {
				ModelLoader.setCustomStateMapper(c, new StateMapperBase() {
		            @Override
		            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		                ResourceLocation regName = new ResourceLocation(c.getRegistryName().toString().substring(buffer));
		                //BaseSeed seed = SEEDS.getValue(regName);
		                //if (seed.getOverrides().getBlockState() != null)
		                //    return new ModelResourceLocation(seed.getOverrides().getBlockState().getPath(), "age=" + state.getValue(BlockResourcefulCrop.AGE));
		                return new ModelResourceLocation(ModBlocks.cropNullanium.getRegistryName(), "age=" + c.getCropAge(state));
		            }
		        });
		
			}
		}
	}
}