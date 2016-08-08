package World;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import Blocks.Block;
import Blocks.Tile;
import Core.Game;
import Player.Camera;

public class World {

	   public Generator generate;
	   public String worldName;
	   public List<Layer> worldLayers = new ArrayList<Layer>();
	   
	   public int seed = 0;
	   
	   public World(String name, Layer... arglayer) {
		   worldName = name;
		   for(Layer layer : arglayer) {
			   worldLayers.add(layer);
		   }
	   }
	   
	   public void fillLayer(String layerName, Block a) {
		   for(int x = 0;x<worldLayers.size();x++) {
			   if(worldLayers.get(x).getName() == layerName) {
				   worldLayers.get(x).fillLayer(a);
			   }
		   }
	   }
	   
	   public void WorldGenerate(String layerName) {
		   Generator Gen = new Generator();
		   seed = Gen.getSeed();
		   
		   for(int x = 0;x<worldLayers.size();x++) {
			   if(worldLayers.get(x).getName() == layerName) {
				   Gen.setSize(worldLayers.get(x).getLengthX(), worldLayers.get(x).getLengthY());
				   Gen.generatePerlin();
				   Gen.generateLandscape(worldLayers.get(x));
				   
				   //worldLayers.get(1).fillLayer(Blocks.dirt);
				   
				   //CaveGenerator GenCa = new CaveGenerator();
				   //GenCa.run();
			   }
		   }
	   }
	   
	   
	   public void WorldSave(List<Layer> layerList) {
		   String path = Game.gameFolder + "/" + Game.saveFolderName;
		   
		   for(int x = 0;x<layerList.size(); x++) {
			   File saveFolder = new File(path + "/" + this.worldName + "/" + "Chunks" + "/" + x);
			   
			   try {
				   saveFolder.mkdirs();
			   } catch (Exception e) { System.out.println("Error: Creating Saves Folder: " + e); }
		   }
		   
		   for(int x = 0;x<layerList.size(); x++) {
			   layerList.get(x).saveLayer(path + "/" + this.worldName + "/" + "Chunks" + "/" + x + "/");
		   }
	   }

	   public void WorldLoad(String worldToLoad) {
		   String path = Game.gameFolder + "/" + Game.saveFolderName;
		   
		   File load = new File(path + "/" + worldToLoad + "/" + "Chunks");
		   File[] loadList = load.listFiles(File::isDirectory);
		   if(loadList != null) {
			   for(int x = 0; x < loadList.length; x++) {
				   worldLayers.get(x).loadLayer(path + "/" + worldToLoad + "/" + "Chunks" + "/" + x + "/");
			   }
		   }
	   }
	   
	   public void LoadChunk(String worldToLoad, int x, int y) {
		   String path = Game.gameFolder + "/" + Game.saveFolderName;
		   
		   File load = new File(path + "/" + worldToLoad + "/" + "Chunks");
		   File[] loadList = load.listFiles(File::isDirectory);
		   if(loadList != null) {
			   for(int l = 0; l < loadList.length; l++) {
				   if(!worldLayers.get(l).isChunkLoaded(x, y)) {
					   worldLayers.get(l).loadChunk(path + "/" + worldToLoad + "/" + "Chunks" + "/" + l + "/", x, y);
				   }
			   }
		   }
	   }
	   
	   public void UnloadChunk(int x, int y) {
		   String path = Game.gameFolder + "/" + Game.saveFolderName;
		   for(int l = 0; l < worldLayers.size(); l++) {
			   if(worldLayers.get(l).isChunkLoaded(x, y)) {
				   worldLayers.get(l).unloadChunk(path + "/" + worldName + "/" + "Chunks" + "/" + l + "/", x, y);
			   }
		   }
	   }
	   
/*	   public void RotateBlocks(String layerName) {
		   int BlockX = (int) Camera.CameraX/Rise.TileSize;
		   int BlockY = (int) Camera.CameraY/Rise.TileSize;
		   
		   Layer tempLayer = getLayer(layerName);
		   
				   for(int x = BlockX; x < BlockX + (Display.getWidth()/Rise.TileSize); x++) {
					   for(int y = BlockY; y < BlockY + (Display.getHeight()/Rise.TileSize); y++) {
						   if(tempLayer.getBlockId(x, y) != 0) {
							   int left = 8; int right = 2; int top = 1; int bottom = 4; int rotation = 0;

							   if(tempLayer.getBlockId(x+1, y) != 0) {
								   rotation+=right;
							   }
							   if(tempLayer.getBlockId(x-1, y) != 0) {
								   rotation+=left;
							   }
							   if(tempLayer.getBlockId(x, y-1) != 0) {
								   rotation+=top;
							   }
							   if(tempLayer.getBlockId(x, y+1) != 0) {
								   rotation+=bottom;
							   }

							   Block temp = tempLayer.getBlock(x, y);	   
							   
							   switch(rotation) {
							   		case 0:
							   			temp.rotation = 0;
							   			break;
							   		case 1:
							   			temp.rotation = 1;
							   			break;
							   		case 2:
							   			temp.rotation = 2;
							   			break;
							   		case 3:
							   			temp.rotation = 12;
							   			break;
							   		case 4:
							   			temp.rotation = 4;
							   			break;
							   		case 5:
							   			temp.rotation = 5;
							   			break;
							   		case 6:
							   			temp.rotation = 9;
							   			break;
							   		case 7:
							   			temp.rotation = 7;
							   			break;
							   		case 8:
							   			temp.rotation = 8;
							   			break;
							   		case 9:
							   			temp.rotation = 6;
							   			break;
							   		case 10:
							   			temp.rotation = 10;
							   			break;
							   		case 11:
							   			temp.rotation = 11;
							   			break;
							   		case 12:
							   			temp.rotation = 3;
							   			break;
							   		case 13:
							   			temp.rotation = 13;
							   			break;
							   		case 14:
							   			temp.rotation = 14;
							   			break;
							   		case 15:
							   			temp.rotation = 15;
							   			break;
							   }
						   }
					   }
				   }
			   }
	   
	   public void fullLighting() {
		   Layer temp = getLayer("Foreground");
		   int BlockX = (int) Camera.CameraX/Rise.TileSize;
		   int BlockY = (int) Camera.CameraY/Rise.TileSize;
		   
		   for(int x = BlockX; x < BlockX+(Display.getWidth()/Rise.TileSize); x++) {
			   for(int y = BlockY; y < BlockY+(Display.getHeight()/Rise.TileSize); y++) {
				   if(!(temp.getBlockId(x, y) == 1)) {
					   temp.setBlockLight(x, y, 0);
				   }
			   }
		   }
		   
		   for(int x = BlockX; x < BlockX+(Display.getWidth()/Rise.TileSize); x++) {
			   for(int y = BlockY; y < BlockY+(Display.getHeight()/Rise.TileSize); y++) {
				   applyLighting(x,y,temp.getBlockLight(x, y));
			   }
		   }
	   }
	   
	   public void applyLighting(int x, int y, int lastLight) {
		   Layer temp = getLayer("Foreground");
		   
		   if(!temp.validPosition(x,y)) return;
		   int newLight = lastLight - (temp.getBlockLightBlock(x, y));
		   if(temp.getBlockId(x, y) == 0) {
			   if(newLight <= temp.getBlockLight(x, y)) return;
		   } else {
			   if(newLight < temp.getBlockLight(x, y)) return;
		   }
		   temp.setBlockLight(x, y, newLight);
		   
		   applyLighting(x+1, y, newLight);
		   applyLighting(x, y+1, newLight);
		   applyLighting(x-1, y, newLight);
		   applyLighting(x, y-1, newLight);
	   } */
	   
	   public void UpdateChunks() {
		   //Get Camera X and Y in Chunks.
		   int ChunkX = (int) Math.floor((Camera.CameraX/Game.TileSize) / Game.ChunkSize) - 1;
		   int ChunkY = (int) Math.floor((Camera.CameraY/Game.TileSize) / Game.ChunkSize) - 1;
		   
		   if(ChunkX < 0) {
			   ChunkX = 0;
		   }
		   
		   if(ChunkY < 0) {
			   ChunkY = 0;
		   }
		   
		   short DisplayXChk = (short) (Math.floor((Display.getWidth()/Game.TileSize) / Game.ChunkSize) + 1);
		   short DisplayYChk = (short) (Math.floor((Display.getHeight()/Game.TileSize) / Game.ChunkSize) + 1);

		   for(int x = ChunkX; x < ChunkX + DisplayXChk; x++) {
			   for(int y = ChunkY; y < ChunkY + DisplayYChk; y++) {
				   LoadChunk(worldName, x, y);
			   }
		   }
	   }
	   
	   public void WorldRender() {
		   //Get Camera X and Y in blocks.
		   int BlockX = (int) (Camera.CameraX/Game.TileSize);
		   int BlockY = (int) (Camera.CameraY/Game.TileSize);
		   
		   //Tile.sky.draw(0, 0, Display.getWidth(), Display.getHeight());
		   //Tile.voidBlock.startUse();
		   for(int l = 0; l < this.worldLayers.size(); l++) {
			   for(int x = BlockX; x < BlockX + Game.DisplayXBlk; x++) {
				   for(int y = BlockY; y < BlockY + Game.DisplayYBlk; y++) {
					   //try {
					   //Tile.requestTiles(worldLayers.get(l).getBlock(x, y).id);
					   //} catch (Exception e) { }
					   
					   worldLayers.get(l).renderBlock(x, y);
					   
				   }
			   }
		   }
		   //Tile.voidBlock.endUse();
	   }
	   
	   
	   public Layer getLayer(String layerName) {
		   for(int l = 0;l < worldLayers.size(); l++) {
			   if(worldLayers.get(l).getName().equals(layerName)) {
				   return worldLayers.get(l);
			   }
		   }
		   return null;
	   }
	   
}
