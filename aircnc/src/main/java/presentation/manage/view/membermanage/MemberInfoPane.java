package presentation.manage.view.membermanage;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.manage.model.MemberManageModel;
import presentation.manage.view.membermanage.fxml.MemberInfoController;

/**
 * this is the pane of member info(main)
 * @author paranoia
 *
 */
public class MemberInfoPane {
	
	private Pane infoLayout;
	private MemberInfoController controller;
	private MemberManageModel model;


	public MemberInfoPane(MemberManageModel model){
		this.model = model;
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberInfo.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = loader.getController();
			infoLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberInfoController getController(){
    	return this.controller;
    }
    
    public Node getContentPane(){
    	return infoLayout;
    }
}

