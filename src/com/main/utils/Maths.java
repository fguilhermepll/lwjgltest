package com.main.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.main.entities.Camera;

public class Maths {
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.translation(translation);
		matrix.rotateXYZ(rx, ry, rz);
		matrix.scale(scale);
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.identity();
		viewMatrix.rotateXYZ((float) Math.toRadians(camera.getPitch()), (float) Math.toRadians(camera.getYaw()), (float) Math.toRadians(camera.getRoll()));
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
		viewMatrix.translate(negativeCameraPos);
		return viewMatrix;
	}

}
