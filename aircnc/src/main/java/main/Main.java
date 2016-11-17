package main;

import javax.swing.JFrame;

import presentation.controller.ProcessOrderViewControllerImpl;
import presentation.view.ProcessOrderView;
import presentation.view.ProcessOrderViewControllerService;

/**
 * 这个是助教写的，不是我写的
 * @author jqwu
 *
 */
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
