package view.gl;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.galaxyws.aircraftdemo.util.Constant;
import com.galaxyws.aircraftdemo.util.OBJLoader;
import com.galaxyws.aircraftdemo.view.ActorModel;
import com.galaxyws.aircraftdemo.view.gl2es2.Builder;
import com.galaxyws.aircraftdemo.view.renderable.Renderable;
import com.jogamp.opengl.util.FPSAnimator;

public class ShaderTest implements GLEventListener {

	private Renderable renderable = null;
	
	private static void initGL() {
		GLCanvas canvas = new GLCanvas();
		canvas.setPreferredSize(new Dimension(Constant.CANVAS_WIDTH,
				Constant.CANVAS_HEIGHT));

		JFrame frame = new JFrame();
		frame.getContentPane().add(canvas);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setTitle(Constant.TITLE);
		frame.pack();
		frame.setVisible(true);

		canvas.addGLEventListener(new ShaderTest());

		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.start();
	}

	public static void main(String[] args) {
		initGL();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
//		// TODO Auto-generated method stub
		GL2ES2 gl = drawable.getGL().getGL2ES2();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//
//		float c = 1.0f;
//		float s = 0.5f;
//
//		// draw a triangle filling the window
//		gl.glBegin(GL.GL_TRIANGLES);
//		gl.glColor3f(1, 0, 0);
//		gl.glVertex2d(-c, -c);
//		gl.glColor3f(0, 1, 0);
//		gl.glVertex2d(0, c);
//		gl.glColor3f(0, 0, 1);
//		gl.glVertex2d(s, -s);
//		gl.glEnd();
		
		renderable.loadIdentity();
		renderable.translate(0.25f * 2 - 1.0f, 0.25f * 2 - 1.0f, 0.0f);
		renderable.scale(0.1f, 0.1f * Constant.RADIO, 1.0f);
		renderable.render(drawable);
	} 

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		try {
			ActorModel model = OBJLoader
					.loadTexturedModel(new File(
							"O:\\hippo\\AircraftDemo\\src\\main\\resources\\com\\galaxyws\\aircraftdemo\\dan.obj"));
			
			Builder.buildTextureRenderableFromModel(drawable, model);
			renderable = model.getRenderable();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		if (height == 0)
			height = 1;
		float aspect = (float) width / height;
		GL2 gl = drawable.getGL().getGL2();

		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		GLU glu = new GLU();
		glu.gluOrtho2D(0.0f, aspect, 0.0f, 1.0f);

	}

}
