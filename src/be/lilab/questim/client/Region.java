package be.lilab.questim.client;

import java.io.Serializable;


public class Region implements Serializable{

	String id, name;
	private int x1 = 0, y1 = 0, width = 0, height = 0;
	String color;
	public Region clone(){
		Region r = new Region(x1,y1,width,height,color);
		r.id = id;
		r.name = name;
		return r;
	}
	
	public Region (){
		
	}
	
	
	public Region (int x, int y, int width, int height, String color){
		this.x1 = x;
		this.y1 = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	public int getX(){
		return x1;
	}
	public int getY(){
		return y1;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	public void setX(int x){
		this.x1 = x;
	}
	public void setY(int y){
		this.y1 = y;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public void setHeight(int height){
		this.height = height;
	}
	
	
}
