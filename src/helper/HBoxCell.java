package helper;

import java.io.IOException;

import controller.adminDashboard;
import controller.memberEdit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class HBoxCell extends HBox {
    Label id = new Label();
    Label name = new Label();
    Label user = new Label();
    Label balance = new Label();
    Label age = new Label();
    Label plan= new Label();
    Button button = new Button();

    public HBoxCell(String idtext, String nametext, String usertext, String balancetext, String agetext, String Plan, String buttonText) {
         super();

         id.setText(idtext);
         id.setMinWidth(50);
         HBox.setHgrow(id, Priority.ALWAYS);
         id.setTextAlignment(TextAlignment.LEFT);
         
         name.setText(nametext);
         name.setMinWidth(100);
         name.setMaxWidth(100);
         name.setTextAlignment(TextAlignment.LEFT);
         
         user.setText(usertext);
         user.setMinWidth(100);
         user.setMaxWidth(100);
         user.setTextAlignment(TextAlignment.LEFT);
         
         balance.setText(balancetext);
         balance.setMinWidth(100);
         balance.setMaxWidth(100);
         balance.setTextAlignment(TextAlignment.LEFT);
         
         age.setText(agetext);
         age.setMinWidth(100);
         age.setMaxWidth(100);
         age.setTextAlignment(TextAlignment.LEFT);
         
         plan.setText(Plan);
         plan.setMinWidth(100);
         plan.setMaxWidth(100);
         plan.setTextAlignment(TextAlignment.LEFT);
         
         button.setText(buttonText);
         button.setMaxWidth(200);
         button.setTextAlignment(TextAlignment.LEFT);
         
         button.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 try {
      				((Node)event.getSource()).getScene().getWindow().hide();
            		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editmember.fxml")); 
     				Stage primaryStage = new Stage();
            		Pane newpane = loader.load();
     				Scene scene = new Scene(newpane);
     				
     				memberEdit controller = (memberEdit) loader.getController();
     				controller.setmember(id.getText());
     				
     				primaryStage.setScene(scene);
     				primaryStage.show();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}     
             }
         });

         this.getChildren().addAll(id,name, user, balance, age, plan, button);
    }
}
