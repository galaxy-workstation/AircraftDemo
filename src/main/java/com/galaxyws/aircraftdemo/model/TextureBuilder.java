package com.galaxyws.aircraftdemo.model;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static javax.media.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static javax.media.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static javax.media.opengl.GL.GL_LINEAR;
import static javax.media.opengl.GL.GL_UNSIGNED_BYTE;
import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL.GL_RGBA;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import com.jogamp.common.nio.Buffers;

public class TextureBuilder {
	
	public static int LoadTextureFromBufferImage(GLAutoDrawable drawable, BufferedImage bufferedImage) {

		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		int[] data = bufferedImage.getRGB(0, 0, w, h, null, 0, w); 
		byte[] byteData = new byte[w * h * 4];
		
		for (int i = 0; i < w * h; i ++)
		{
			byteData[4 * i + 0] = (byte) (data[i] >> 24);
			byteData[4 * i + 1] = (byte) (data[i] >> 16);
			byteData[4 * i + 2] = (byte) (data[i] >> 8);
			byteData[4 * i + 3] = (byte) (data[i] >> 0);
		}
		
		GL2 gl = drawable.getGL().getGL2();
		
		IntBuffer texId = Buffers.newDirectIntBuffer(1);
		gl.glGenTextures(1, texId);
		gl.glBindTexture(GL_TEXTURE_2D, texId.get(0));
		gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, ByteBuffer.wrap(byteData));
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

		gl.glBindTexture(GL_TEXTURE_2D, 0);
		return texId.get(0);
	}
}
