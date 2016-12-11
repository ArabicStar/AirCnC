package launcher;

import javafx.application.Application;
import presentation.member.CenterController;

public class UILauncher {
	public static final void launch() {
		Application.launch(CenterController.class);
	}

	private UILauncher() {
	}
}
