package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import helper.SBoxCell;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Member;
import model.Play;
import model.Show;

public class selectshow {
	Member currentUser = new Member(null, null, null, null, null, null, null);
	ArrayList<Member> userlist = new ArrayList<Member> ();
	ArrayList<Play> playlist = new ArrayList<Play> ();
	public Play currentPlay = new Play(null, null, null);
	
	@FXML private Pane commonPane;
	@FXML private Label playid;
	@FXML private Label playtitle;
	@FXML private Label playprice;
	@FXML private ListView<SBoxCell> showList;
	@FXML private Label welcome;
	protected ListProperty<SBoxCell> showProperty = new SimpleListProperty<>();
	
	
	public void setPlay(String text) {
		// TODO Auto-generated method stub
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
		        	 System.out.println("Current user id ---> "+currentUser.id.toString());
		        	 temp.add(new SBoxCell(shows.get(i).id.toString(), shows.get(i).screenname.toString(), shows.get(i).time.toString(), shows.get(i).showtype.toString(), true, currentUser.id.toString(), currentPlay.id.toString()));
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
	
	public void dashboard(ActionEvent event){
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/memberDashboard.fxml")); 
			Stage primaryStage = new Stage();
    		Pane newpane = loader.load();
			Scene scene = new Scene(newpane);
				
			memberdashboard userController = (memberdashboard) loader.getController();
			userController.setmember(currentUser.id.toString());
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void setMember(String id) {
		try{
			  
	         FileInputStream fileIn = new FileInputStream("Theatre.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn); 
	         userlist = (ArrayList<Member>) in.readObject();
	         in.close();
	         fileIn.close();
	         
	         for(int i=0; i< userlist.size();i++){
		    	  if(id.toString().equals(userlist.get(i).id)){
		    		  System.out.println("saved the current user");
		    		  currentUser = userlist.get(i);
		    	  }
		      }
	         
	         welcome.setText("Account Balance : "+ currentUser.balance.toString());
		 		

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
