package com.theders.dersdroidengine.components;

public abstract class RenderableComponent extends Component{
	
	public RenderableComponent(String tag){
		super(tag);
	}

	public abstract void render();

}
