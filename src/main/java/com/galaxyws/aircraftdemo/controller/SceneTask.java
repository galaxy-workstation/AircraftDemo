package com.galaxyws.aircraftdemo.controller;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.galaxyws.aircraftdemo.Main;
import com.galaxyws.aircraftdemo.util.Constant;
import com.galaxyws.aircraftdemo.view.gl.Scene;

public class SceneTask implements Runnable {

	private Main hook;

	public SceneTask(Main hook) {
		this.hook = hook;
	}

	@Override
	public void run() {
		GLCanvas canvas = new Scene();
		canvas.setPreferredSize(new Dimension(Constant.CANVAS_WIDTH,
				Constant.CANVAS_HEIGHT));

		JFrame frame = new JFrame();
		frame.getContentPane().add(canvas);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Thread(new CloseTask()).start();
			}
		});
		frame.setTitle(Constant.TITLE);
		frame.pack();
		frame.setVisible(true);
		while (true) {
			canvas.display();
		}
	}

	private class CloseTask implements Runnable {
		@Override
		public void run() {
			hook.stop();
		}
	}

}
