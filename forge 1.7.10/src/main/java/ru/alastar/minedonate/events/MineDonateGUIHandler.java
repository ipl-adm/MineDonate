package ru.alastar.minedonate.events;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.gui.ShopGUI;
import ru.alastar.minedonate.gui.merge.ShopInventoryContainer;
import ru.alastar.minedonate.gui.merge.ShopInventoryGUI;

public class MineDonateGUIHandler implements IGuiHandler {
    public static final int SHOP_ID = 0, STORE_ID = 1 ;

    @Override
    public Object getServerGuiElement ( int id, EntityPlayer player, World world, int x, int y, int z) {

    	if ( id == STORE_ID ) {
    		
    		ShopInventoryContainer sic = new ShopInventoryContainer ( player . inventory ) ;
    		
    		MineDonate . mergeContainers . put ( player . getDisplayName ( ), sic ) ;
    		
        	return sic ;
        
        }

        return null;
    }

    ShopGUI lastOpened ;
    
    @Override
    public Object getClientGuiElement ( int id, EntityPlayer player, World world, int x, int y, int z) {
    	
        if ( id == SHOP_ID ) {

        	if ( lastOpened != null ) {
        		
        		lastOpened . onGuiClosed ( ) ;
        		
        	}

        	lastOpened = new ShopGUI ( ) ;//( ShopContainer ) getServerGuiElement ( ID, player, world, x, y, x ) ) ) ;
        //	Minecraft.getMinecraft().displayGuiScreen(lastOpened);
        	return lastOpened ;
        	
        } else if ( id == STORE_ID ) {
        	
        	return new ShopInventoryGUI ( player . inventory ) ; // ( ShopInventoryContainer ) getServerGuiElement ( id, player, world, x, y, z )
        	
        }
        

        return null ;

    }

}