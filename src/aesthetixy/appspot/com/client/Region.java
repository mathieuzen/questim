package aesthetixy.appspot.com.client;

public class Region {

	String id, name;
	private int x1 = 0, y1 = 0, width = 0, height = 0;
	public Region clone(){
		Region r = new Region(x1,y1,width,height);
		r.id = id;
		r.name = name;
		return r;
	}
	
	public Region (int x, int y, int width, int height){
		this.x1 = x;
		this.y1 = y;
		this.width = width;
		this.height = height;
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
	
	
}
