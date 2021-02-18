package com.main.shaders;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/com/main/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/com/main/shaders/fragmentShader.txt";

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

}