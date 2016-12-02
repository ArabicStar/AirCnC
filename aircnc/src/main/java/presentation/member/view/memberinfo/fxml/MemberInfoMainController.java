package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.member.CenterController;
import presentation.member.ClientCenterController;

public class MemberInfoMainController implements Initializable{
	
	@FXML
	private Button modify;
	
	@FXML
	private Label username;
	
	@FXML
	private Label name;
	
	@FXML
	private Label tele;
	
	@FXML
	private Label mobi;
	
	@FXML
	private Label email;
	
	@FXML
	private Label credit;
	
	private ClientCenterController controller;
	
	private String memberName;
	
	private String memberUsername;
	
	private String memberTele;
	
	private String memberMobi;
	
	private String memberEmail;
	
	private int memberCredit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initMemberInfo();
			  }
		});		
	}
	
	@FXML
	public void handleModify(){
		controller.addInfoModifyPane();
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(ClientCenterController centerController) {
		// TODO Auto-generated method stub
		this.controller=centerController;
	}
	
	public void setUsername(String username){
		this.memberUsername = username;
	}
	
	public void setName(String name){
		this.memberName = name;
	}
	
	public void setTele(String tele){
		this.memberTele = tele;
	}
	
	public void setMobi(String mobi){
		this.memberMobi = mobi;
	}
	
	public void setEmail(String email){
		this.memberEmail = email;
	}
	
	public void setCredit(int credit){
		this.memberCredit = credit;
	}
	
	private void initMemberInfo(){
		username.setText(memberUsername);
		name.setText(memberName);
		tele.setText(memberTele);
		mobi.setText(memberMobi);
		email.setText(memberEmail);
		credit.setText(String.valueOf(memberCredit));
	}
}
