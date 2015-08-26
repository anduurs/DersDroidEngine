package com.theders.dersdroidengine.components;

import com.theders.dersdroidengine.view.GameView;

public class BasicMovement extends Component{
	
	private float m_Speed;
	
	public BasicMovement(float speed){
		super("BasicMovement");
		m_Speed = speed;
	}
	
	public void update(float dt){
		float x = m_GameObject.getTransform().getPosition().x;
		float y = m_GameObject.getTransform().getPosition().y;
		if(x >= GameView.WIDTH)
			m_GameObject.destroy();
		x += m_Speed * dt;
		m_GameObject.getTransform().translate(x, y, 0);
	}

}
