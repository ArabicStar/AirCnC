package presentation.member.view.myorder.utils;

import javafx.scene.control.Button;

public class FunctionButtons extends Button{
	
	public FunctionButtons(String content,boolean caution){
		this.setText(content);
		
		if(caution){
			//#F0787A
			this.setStyle("-fx-background-color: #F0787A;");
		}else{
			//#585697
			this.setStyle("-fx-background-color: #585697;");
		}
		
		this.setStyle("-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-background-color: #F0787A; -fx-border-radius: 5; -fx-background-radius: 20;");
	}
	
}
