package helper;

import java.io.IOException;

import controller.addShow;
import controller.memberEdit;
import controller.selectshow;
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

public class PBoxCell extends HBox {
    Label id = new Label();
    Label name = new Label();
    Label description = new Label();
    Label price = new Label();
    Label shows = new Label();
    Label playtype = new Label();
    Button button = new Button();

    public PBoxCell(String idtext, String nametext, String desc, String amount, String showno, String type, String buttonText, boolean admincheck, String userid) {
         super();

         id.setText(idtext);
         id.setMinWidth(50);
         id.setMaxWidth(50);
         HBox.setHgrow(id, Priority.ALWAYS);
         id.setTextAlignment(TextAlignment.LEFT);
         
         name.setText(nametext);
         name.setMinWidth(100);
         name.setMaxWidth(100);
         name.setTextAlignment(TextAlignment.LEFT);
         
         description.setText(desc);
         description.setMinWidth(125);
         description.setMaxWidth(125);
         description.setTextAlignment(TextAlignment.LEFT);
         
         price.setText(amount);
         price.setMinWidth(100);
         price.setMaxWidth(100);
         price.setTextAlignment(TextAlignment.LEFT);
         
         shows.setText(showno);
         shows.setMinWidth(75);
         shows.setMaxWidth(75);
         shows.setTextAlignment(TextAlignment.LEFT);
         
         playtype.setText(type);
         playtype.setMinWidth(100);
         playtype.setMaxWidth(100);
         playtype.setTextAlignment(TextAlignment.LEFT);
         
         button.setText(buttonText);
         button.setMaxWidth(200);
         button.setTextAlignment(TextAlignment.LEFT);
         
         
         button.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 try {
      				((Node)event.getSource()).getScene().getWindow().hide();
      				FXMLLoader loader;
      				if(admincheck == true){
      					loader = new FXMLLoader(getClass().getResource("/view/addshow.fxml")); 
      				}else{
      					loader = new FXMLLoader(getClass().getResource("/view/selectshow.fxml")); 
      				}
      					
     				Stage primaryStage = new Stage();
            		Pane newpane = loader.load();
     				Scene scene = new Scene(newpane);
     				if(admincheck == true){
     					addShow controller = (addShow) loader.getController();
     					controller.setPlay(id.getText());
     				}else{
     					selectshow controller2 = (selectshow) loader.getController();
     					controller2.setMember(userid);
     					controller2.setPlay(id.getText());
     				}
     				
     				primaryStage.setScene(scene);
     				primaryStage.show();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}     
             }
         });

         this.getChildren().addAll(id,name, description, price, shows,playtype, button);
    }
}

