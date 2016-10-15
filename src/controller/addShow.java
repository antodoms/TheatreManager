package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import helper.KeyValuePair;
import helper.PBoxCell;
import helper.SBoxCell;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Play;
import model.Show;

public class addShow {

	ArrayList<Play> playlist = new ArrayList<Play> ();
	public Play currentPlay = new Play(null, null, null);
	
	@FXML private Pane commonPane;
	@FXML private Label playid;
	@FXML private Label playtitle;
	@FXML private Label playprice;
	@FXML private ListView<SBoxCell> showList;
	protected ListProperty<SBoxCell> showProperty = new SimpleListProperty<>();
	@FXML private TextField showscreenname;
	@FXML private DatePicker showdate;
	@FXML private TextField showscreentype;
	
	
	@FXML private TextField newbalance;
	@FXML private ChoiceBox<KeyValuePair> newplan;
	
	public void setPlay(String text) {
		try{
			  
	         FileInputStream fileIn = new FileInputStream("Play.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn); 
	         playlist = (ArrayList<Play>) in.readObject();
	         in.close();
	         fileIn.close();
	         
	         for(int i=0; i< playlist.size();i++){
	        	 if(playlist.get(i).id.toString().equals(text.toString())){
	        		 currentPlay = playlist.get(i);
	        	 }
	         }
	         
	         playid.setText(currentPlay.id.toString());
	         playtitle.setText(currentPlay.name.toString());
	         playprice.setText(currentPlay.price.toString());
	         
	         ArrayList<SBoxCell> temp = new ArrayList<>();
	         ArrayList<Show> shows = currentPlay.getshowlist();
	         if(shows.size()>0){
		         for(int i=0; i<shows.size();i++){
		        	 temp.add(new SBoxCell(shows.get(i).id.toString(), shows.get(i).screenname.toString(), shows.get(i).time.toString(), shows.get(i).showtype.toString(), false,"",""));
		         }
		         
		         showList.itemsProperty().bind(showProperty);
		         showProperty.set(FXCollections.observableArrayList(temp));
	         }
	         
	      }catch(IOException i){
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c){
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	}
	
	public void addShow(ActionEvent event){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		LocalDate date = showdate.getValue();
		Show temp = new Show(showscreenname.getText(), formatter.format(date),showscreentype.getText());
		try{
	         FileInputStream fileIn = new FileInputStream("Play.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn); 
	         playlist = (ArrayList<Play>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i){
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c){
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
		
		for(int i=0; i< playlist.size();i++){
			if(playlist.get(i).id.toString().equals(currentPlay.id.toString())){
				temp.id = ""+playlist.size();
				playlist.get(i).addshow(temp);
			}
		}
		
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
			((Node)event.getSource()).getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addshow.fxml")); 
			Stage primaryStage = new Stage();
    		Pane newpane = loader.load();
			Scene scene = new Scene(newpane);
				
			addShow controller = (addShow) loader.getController();
			System.out.println("play id ---> "+currentPlay.id.toString());
			controller.setPlay(currentPlay.id.toString());	
			primaryStage.setScene(scene);
			primaryStage.show();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void dashboard(ActionEvent event){
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

}
