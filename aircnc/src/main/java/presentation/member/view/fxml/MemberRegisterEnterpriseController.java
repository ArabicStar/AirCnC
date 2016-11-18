package presentation.member.view.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MemberRegisterEnterpriseController implements Initializable{
	
	@FXML
	private TextField enterprise;
	
	@FXML
	private Button confirm;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enterprise.setText("企业名称");
		//监听键入
	}
	
	@FXML
	public void handleConfirm(){
		//这里将键入的数据传入逻辑层，跳转至memberSignInPane。
		
	}
}
