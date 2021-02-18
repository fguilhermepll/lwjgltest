package com.main;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.Version;

import com.main.models.RawModel;
import com.main.models.TexturedModel;
import com.main.renderEngine.Loader;
import com.main.renderEngine.Renderer;
import com.main.shaders.StaticShader;
import com.main.textures.ModelTexture;

public class GameRun {
	
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		
		DisplayManager display = new DisplayManager();
		//Needed to create the current context for OpenGL to operate on.
		display.init();
		display.loop();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
			    -0.5f, 0.5f, 0f,
			    -0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    0.5f, 0.5f, 0f
			  };
		
		int[] indices = {
				0,1,3,
				3,1,2
		};
		
		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		//Main Game Loop
		while ( !glfwWindowShouldClose(display.getWindow()) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			
			//GL11.glPolygonMode(GLES20.GL_FRONT_AND_BACK, GL11.GL_LINE); // WIREFRAME MODE
			
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			glfwSwapBuffers(display.getWindow()); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
		
		shader.cleanUp();
		display.destroy();
		loader.cleanUp();
	}

	public static void main(String[] args) {
		new GameRun().run();
	}

}