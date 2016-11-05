package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentation.controller.ProcessOrderViewControllerImpl;
import presentation.view.ProcessOrderView;
import presentation.view.ProcessOrderViewControllerService;

public class Main {
	
	public static void main(String[] args){
		JFrame mFrame = new JFrame();
		mFrame.setSize(800, 600);
		mFrame.setLocation(300, 300);
		int hotelId = 1;
		ProcessOrderViewControllerService controller = new ProcessOrderViewControllerImpl(hotelId);
		ProcessOrderView view = new ProcessOrderView(controller);
		controller.setView(view);
		mFrame.getContentPane().add(view);
		mFrame.setVisible(true);
	
	}

}
