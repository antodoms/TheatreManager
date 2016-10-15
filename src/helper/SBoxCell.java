package helper;

import java.io.IOException;

import controller.addShow;
import controller.bookingpage;
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

public class SBoxCell extends HBox {
	Label id = new Label();
    Label name = new Label();
    Label time = new Label();
    Label playtype = new Label();
    Button button = new Button();

    public SBoxCell(String idtext, String nametext, String timetext, String playtext, Boolean membercheck, String userid, String playid) {
         super();

         id.setText(idtext);
         id.setMinWidth(100);
         id.setMaxWidth(100);
         HBox.setHgrow(id, Priority.ALWAYS);
         id.setTextAlignment(TextAlignment.LEFT);
         
         name.setText(nametext);
         name.setMinWidth(180);
         name.setMaxWidth(180);
         name.setTextAlignment(TextAlignment.LEFT);
         
         time.setText(timetext);
         time.setMinWidth(160);
         time.setMaxWidth(160);
         time.setTextAlignment(TextAlignment.LEFT);
         
         playtype.setText(playtext);
         playtype.setMinWidth(150);
         playtype.setMaxWidth(150);
         playtype.setTextAlignment(TextAlignment.LEFT);
         
         button.setText("Book");
         button.setMaxWidth(200);
         button.setTextAlignment(TextAlignment.LEFT);
         
         button.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 try {
      				((Node)event.getSource()).getScene().getWindow().hide();
            		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bookingpage.fxml")); 
     				Stage primaryStage = new Stage();
            		Pane newpane = loader.load();
     				Scene scene = new Scene(newpane);
     				
     				bookingpage controller = (bookingpage) loader.getController();
     				System.out.println("user id ---->"+userid.toString());
     				controller.setPlay(playid.toString(), id.getText());
     				controller.setmember(userid.toString());
     	
     				
     				primaryStage.setScene(scene);
     				primaryStage.show();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}     
             }
         });
         if(membercheck == true){
        	 this.getChildren().addAll(id,name, time,playtype, button);
         }else{
        	 this.getChildren().addAll(id,name, time,playtype);
         }
    }
}
