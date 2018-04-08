package com.nicjames2378.DynamicCrops.proxy;

import org.apache.commons.lang3.math.NumberUtils;

import com.nicjames2378.DynamicCrops.DynamicPlants;
import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.baseClasses.BaseSeed;
import com.nicjames2378.DynamicCrops.baseClasses.BaseCrop;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public void PreInit(FMLPreInitializationEvent event) {
		super.PreInit(event);	
	}
	
	@Override
	public void Init(FMLInitializationEvent event) {
		super.Init(event);
		((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(COLOR_RELOADER);
	}	
	
	@Override
	public void PostInit(FMLPostInitializationEvent event) {
		super.PostInit(event);
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + id, "inventory"));
	}
	
	private static final IResourceManagerReloadListener COLOR_RELOADER = resourceManager -> {
        for (BaseSeed seed : DynamicPlants.getSeedsList()) {
            if (seed.isDynamic) {
            	int seedIndex = DynamicPlants.getSeedsList().indexOf(seed);
            	
                ItemStack base = new ItemStack(DynamicPlants.getCropsList().get(seedIndex).getCrop()); //seed.getInputItems().get(0);
                int color = getStackColor(base);
                seed.setColor(color);
                Main.logger.debug("Generated color {} for seed {} based on the stack {}", color, seed.getRegistryName(), base);
            }
        }
    };
    
	public static int getStackColor(ItemStack stack) {
    	IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, null, null);
	    TextureAtlasSprite sprite = model.getParticleTexture();
	    if (sprite == null) { // Item doesn't have a TAS and is rendered some other way.
	    	Main.logger.debug("[ERROR] Error generating color from stack {} (Null TAS)", stack);
	        return 0xFF000000;
	    }
	    int[] pixels = sprite.getFrameTextureData(0)[0];
	    int r = 0, g = 0, b = 0, count = 0;
	    for (int argb : pixels) {
	        int ca = argb >> 24 & 0xFF;
	        int cr = argb >> 16 & 0xFF;
	        int cg = argb >> 8 & 0xFF;
	        int cb = argb & 0xFF;
	        if (ca > 0x7F && NumberUtils.max(cr, cg, cb) > 0x1F) {
	            r += cr;
	            g += cg;
	            b += cb;
	            count++;
	        }
	    }
	    if (count > 0) {
	        r /= count;
	        g /= count;
	        b /= count;
	    }
	    return 0xFF000000 | r << 16 | g << 8 | b;
	}
}
