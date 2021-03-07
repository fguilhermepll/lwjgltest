package com.main;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.joml.Vector3f;
import org.lwjgl.Version;

import com.main.entities.Camera;
import com.main.entities.Entity;
import com.main.entities.Light;
import com.main.models.RawModel;
import com.main.models.TexturedModel;
import com.main.renderEngine.Loader;
import com.main.renderEngine.OBJLoader;
import com.main.renderEngine.Renderer;
import com.main.shaders.StaticShader;
import com.main.textures.ModelTexture;

public class GameRun {
	
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		
		DisplayManager display = new DisplayManager();
		
		display.init();		
		//Needed to create the current context for OpenGL to operate on.
		//
		//GL.createCapabilities();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		
		//RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		//ModelTexture texture = new ModelTexture(loader.loadTexture("pexels-photo-129733"));
		
		RawModel model = OBJLoader.loadObjModel("tabel", loader);
		TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("woody")));
		
		//RawModel model = OBJLoader.loadObjModel("monkey", loader);
		//TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
		
		ModelTexture texture = texturedModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(100);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0,-3,-10),0.2f,0,0,1);
		Light light = new Light(new Vector3f(0,0.3f,1), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		//Main Game Loop
		while ( !glfwWindowShouldClose(DisplayManager.getWindow()) ) {
			display.loop();
			
			shader.start();
			camera.move();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			renderer.render(entity,shader);
			entity.increaseRotation(0, 0.02f, 0);
			shader.stop();
			glfwSwapBuffers(DisplayManager.getWindow()); // swap the color buffers

			// Poll for window events. The key callback will only be
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