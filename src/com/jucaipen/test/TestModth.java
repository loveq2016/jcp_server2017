package com.jucaipen.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class TestModth extends JFrame {
	private JButton btn1 = new JButton("Button 1");
	private JButton btn2 = new JButton("Button 2");
	private Robot robot;

	public TestModth() {
		setLayout(new GridLayout(0, 1));
		add(btn1);
		add(btn2);

		btn1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON1)
					System.out.println(btn1.getText() + "鼠标左键点击");
				if (event.getButton() == MouseEvent.BUTTON3)
					System.out.println(btn1.getText() + "鼠标右键点击");
			}
		});
		btn2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON1)
					System.out.println(btn2.getText() + "鼠标左键点击");
				if (event.getButton() == MouseEvent.BUTTON3)
					System.out.println(btn2.getText() + "鼠标右键点击");
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent event) {
			}
		});
	}

	public void simulationClick() throws AWTException {
		// 模拟鼠标点击代码
		Point p1 = btn1.getLocation();
		Point p2 = btn2.getLocation();
		System.out.println(p1.x + "," + p1.y);
		System.out.println(p2.x + "," + p2.y);
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = p.x;
		int y = p.y;
		robot = new Robot();
		System.out.println(x + "," + y);
		robot.mouseMove(p1.x + 10, p2.y + 10);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.mouseMove(x, y);
	}


	public static void main(String[] args) {
		Robot robot;
		try {
			robot = new Robot();
			robot.mouseMove(190, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
