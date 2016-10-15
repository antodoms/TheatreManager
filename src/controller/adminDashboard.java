package controller;

import helper.HBoxCell;
import helper.PBoxCell;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.LocalPlay;
import model.Member;
import model.Play;
import model.User;

public class adminDashboard {
	ArrayList<Member> userlist = new ArrayList<Member> ();
	ArrayList<Play> playlist = new ArrayList<Play> ();
	
	private final ObservableList<Member> data = FXCollections.observableArrayList();
	private final ObservableList<Play> data2 = FXCollections.observableArrayList();
	@FXML private Pane commonPane;
	
	@FXML private ListView<HBoxCell> TableList;
	protected ListProperty<HBoxCell> listProperty = new SimpleListProperty<>();
	
	@FXML private ListView<PBoxCell> PlayList;
	protected ListProperty<PBoxCell> PlayProperty = new SimpleListProperty<>();
	
	public void addMember (ActionEvent event) throws IOException {
		try {
			Pane newpane = FXMLLoader.load(getClass().getResource("/view/addmember.fxml"));
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
	
	public void addPlay(ActionEvent event) throws IOException {
		try {
			Pane newpane = FXMLLoader.load(getClass().getResource("/view/addplay.fxml"));
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
	
	public void SignOut(ActionEvent event){
	
		
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root;
			root = loader.load(getClass().getResource("/view/login.fxml"));
			adminDashboard userController = (adminDashboard) loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML
	public void initialize() throws EOFException{
		try{
			
			//Theatre Data
	         FileInputStream fileIn = new FileInputStream("Theatre.ser");
	         int length=0;
	         if( fileIn.available() > 0){
	        	 length++;
	         }
	         if(length!=0){
			         ObjectInputStream in = new ObjectInputStream(fileIn); 
			         userlist = (ArrayList<Member>) in.readObject();
			         in.close();
			         fileIn.close();
			         if(userlist.size()>0){
						data.addAll(userlist);
						ArrayList<HBoxCell> temp = new ArrayList<>();
						for(int i=0; i< userlist.size();i++){
							temp.add(new HBoxCell(userlist.get(i).id, userlist.get(i).name, userlist.get(i).username, userlist.get(i).balance, userlist.get(i).age, userlist.get(i).getplan(), "> GO"));
							}
						TableList.itemsProperty().bind(listProperty);
						listProperty.set(FXCollections.observableArrayList(temp));
			         }
	         }
			//Play Data
			
			FileInputStream playIn = new FileInputStream("Play.ser");
			length=0;
	         if( playIn.available() > 0){
	        	 length++;
	         }
	         if(length!=0){
		         ObjectInputStream pin = new ObjectInputStream(playIn); 
		         playlist = (ArrayList<Play>) pin.readObject();
		         pin.close();
		         playIn.close();
		         System.out.println("size --> "+playlist.size());
		         if(playlist.size()>0){
						data2.addAll(playlist);
						ArrayList<PBoxCell> temp2 = new ArrayList<>();
						for(int i=0; i< playlist.size();i++){
							if(playlist.get(i) instanceof LocalPlay){
								temp2.add(new PBoxCell(playlist.get(i).id, playlist.get(i).name, playlist.get(i).Description, playlist.get(i).price, playlist.get(i).getshowno(),"Local", "> GO", true,""));
							}else{
								temp2.add(new PBoxCell(playlist.get(i).id, playlist.get(i).name, playlist.get(i).Description, playlist.get(i).price, playlist.get(i).getshowno(),"International", "> GO", true,""));
							}
							
						}
						PlayList.itemsProperty().bind(PlayProperty);
						PlayProperty.set(FXCollections.observableArrayList(temp2));
		         }
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
}


	
	
	
	


