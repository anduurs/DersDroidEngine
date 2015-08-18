package com.theders.dersdroidengine.components;

public class BasicMovement extends Component{
	
	private float m_Speed;
	
	public BasicMovement(float speed){
		super("BasicMovement");
		m_Speed = speed;
	}
	
	public void update(float dt){
		float x = m_GameObject.getTransform().getPosition().x;
		x += m_Speed * dt;
		m_GameObject.getTransform().translate(x, 0, 0);
	}

}
