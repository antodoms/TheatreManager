package helper;

import java.io.IOException;

import controller.memberEdit;
import controller.memberdashboard;
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

public class BBoxCell extends HBox{
	Label id = new Label();
    Label moviename = new Label();
    Label showscreen = new Label();
    Label showtime = new Label();
    Label totalprice = new Label();
    Button button = new Button();

    public BBoxCell(String idtext, String movienametext, String showscreentext, String showtimetext, String totalpricetext, String userid) {
         super();

         id.setText(idtext);
         id.setMinWidth(50);
         HBox.setHgrow(id, Priority.ALWAYS);
         id.setTextAlignment(TextAlignment.LEFT);
         
         moviename.setText(movienametext);
         moviename.setMinWidth(120);
         moviename.setMaxWidth(120);
         moviename.setTextAlignment(TextAlignment.LEFT);
         
         showscreen.setText(showscreentext);
         showscreen.setMinWidth(125);
         showscreen.setMaxWidth(125);
         showscreen.setTextAlignment(TextAlignment.LEFT);
         
         showtime.setText(showtimetext);
         showtime.setMinWidth(130);
         showtime.setMaxWidth(130);
         showtime.setTextAlignment(TextAlignment.LEFT);
         
         totalprice.setText(totalpricetext);
         totalprice.setMinWidth(90);
         totalprice.setMaxWidth(90);
         totalprice.setTextAlignment(TextAlignment.LEFT);
         
         button.setText("Delete Booking");
         button.setMaxWidth(200);
         button.setTextAlignment(TextAlignment.LEFT);
         
         button.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 try {
      				((Node)event.getSource()).getScene().getWindow().hide();
            		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/memberDashboard.fxml")); 
     				Stage primaryStage = new Stage();
            		Pane newpane = loader.load();
     				Scene scene = new Scene(newpane);
     				
     				memberdashboard controller = (memberdashboard) loader.getController();
     				controller.setmember(userid.toString());
     				controller.deletebooking(id.getText());
     				controller.setmember(userid.toString());
     				primaryStage.setScene(scene);
     				primaryStage.show();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}     
             }
         });

         this.getChildren().addAll(id,moviename, showscreen, showtime, totalprice, button);
    }
}
