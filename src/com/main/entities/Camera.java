package com.main.entities;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_REPEAT;

import org.joml.Vector3f;

import com.main.DisplayManager;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	private float speed = 0.1f;
	
	public Camera() {}

	public void move() {
		
		glfwSetKeyCallback(DisplayManager.getWindow(), (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_PRESS )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
			
			if ( key == GLFW_KEY_W && action == GLFW_REPEAT )
				position.z-=speed;
			if ( key == GLFW_KEY_S && action == GLFW_REPEAT )
				position.z+=speed;

			if( key == GLFW_KEY_D && action == GLFW_REPEAT ) {
				position.x+=speed;
			}
			if( key == GLFW_KEY_A && action == GLFW_REPEAT ) {
				position.x-=speed;
			}
		});
	}
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	

}
