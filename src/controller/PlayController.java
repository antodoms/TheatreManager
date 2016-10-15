package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import helper.KeyValuePair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ForeignPlay;
import model.LocalPlay;
import model.Member;
import model.Play;

public class PlayController {
	
	
	ArrayList<Play> playlist = new ArrayList<Play> ();
	@FXML
	private AnchorPane commonPane;
	@FXML
	private TextField playtitle;
	@FXML
	private TextField playdescription;
	@FXML
	private TextField playprice;
	@FXML
	private ChoiceBox<KeyValuePair> playtype;

	Play newPlay= new Play(null,null,null);
	
	
	
	public void AddPlay(ActionEvent event){
		String check = playtype.getValue().getKey().toString();
		if(check.equals(new String("1"))){
			newPlay = new LocalPlay(playtitle.getText(), playdescription.getText(), playprice.getText());
		}else{
			newPlay = new ForeignPlay(playtitle.getText(), playdescription.getText(), playprice.getText());
		}
		
		  try{
			  
	         FileInputStream fileIn = new FileInputStream("Play.ser");
	         int length=0;
	         if( fileIn.available() > 0){
	        	 length++;
	         }
	         if(length!=0){
		         ObjectInputStream in = new ObjectInputStream(fileIn); 
		         playlist = (ArrayList<Play>) in.readObject();
		         in.close();
		         fileIn.close();
	         }
	      }catch(IOException i){
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c){
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	      
	      newPlay.setid(""+playlist.size());
	      playlist.add(newPlay);
	      
	      
	      try
	      {
	         FileOutputStream fileOut = new FileOutputStream("Play.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(playlist);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /Theatre.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	      
	      
	      try {
				Pane newpane = FXMLLoader.load(getClass().getResource("/view/adminDashboard.fxml"));
				Scene scene = new Scene(newpane);
				Stage primaryStage = (Stage) commonPane.getScene().getWindow();
				//Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
	}
	
	@FXML
	public void initialize() {
		playtype.getItems().add(new KeyValuePair("1", "Local Play"));
		playtype.getItems().add(new KeyValuePair("2", "Foreign Play"));
	}
	
	

}
