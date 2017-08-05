package ar.edu.usal.tp9.main;

import ar.edu.usal.tp9.controller.MenuController;
import ar.edu.usal.tp9.view.MenuView;

public class Principal {

	public static void main(String[] args) {

		MenuController menuController = new MenuController();
		MenuView menuView = new MenuView(menuController);
	

	}

}
