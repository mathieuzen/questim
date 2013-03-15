package be.lilab.questim.client;

import java.util.ArrayList;

public class RegionNode {
	public ArrayList<RegionNode> children;
	public RegionNode parent;
	public Region r;
	
	public RegionNode(Region r){
		this(r, null);
	}
	
	public RegionNode(Region r, RegionNode parent){
		this.r = r;
		this.parent = parent;
	}
	
	public RegionNode addChild(Region child){
		if(children==null){
			children = new ArrayList<RegionNode>();
		}
		RegionNode childrenItem = new RegionNode(child,this);
		children.add(childrenItem);
		return childrenItem;
	}
	
	public RegionNode addSibling(Region sibling){
		if(parent==null){
			return null;
		}
		return parent.addChild(sibling);
	}
	
	public RegionNode getParent(){
		return parent;	
	}
	
	public ArrayList getChildren(){
		return children;	
	}
}
