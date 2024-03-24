package org.dmitriwamback.initialplugin;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Vindicator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.*;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {
	
	@Override public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler public void onJoin(PlayerJoinEvent event) {
		Bukkit.broadcastMessage(event.getPlayer().getName());
	}
	
	@EventHandler public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if (damager instanceof Vindicator) {
			damager.getWorld().createExplosion(damager.getLocation(), 6, true);
		}
	}
	
	
	
	public void fallBlocks(List<Block> blocks) {
		
		for (Block block : blocks) {
			float x =  (float)(Math.random() - 0.5);
			float y =  (float)(Math.random() - 0.2);
			float z =  (float)(Math.random() - 0.5);
			
			FallingBlock fallingBlock = block.getWorld().spawn(block.getLocation(), FallingBlock.class);
			fallingBlock.setVelocity(new Vector(x, y, z));
			block.setType(Material.AIR);
		}
		
	}
	
	
	
	@EventHandler public void onEntityExplode(EntityExplodeEvent event) {
		
		fallBlocks(event.blockList());
	}
	
	@EventHandler public void onExplode(BlockExplodeEvent event) {
		
		fallBlocks(event.blockList());
	}
}
