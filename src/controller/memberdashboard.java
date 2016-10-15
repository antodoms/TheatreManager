package controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import helper.BBoxCell;
import helper.HBoxCell;
import helper.PBoxCell;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Booking;
import model.LocalPlay;
import model.Member;
import model.Play;
import model.Show;

public class memberdashboard {

	ArrayList<Play> playlist = new ArrayList<Play> ();
	ArrayList<Member> userlist = new ArrayList<Member> ();
	private final ObservableList<Play> data = FXCollections.observableArrayList();
	private final ObservableList<Booking> data2 = FXCollections.observableArrayList();

	@FXML private Label welcome;
	@FXML private Pane commonPane;
	
	@FXML private ListView<PBoxCell> PlayList;
	@FXML private ListView<BBoxCell> bookinglist;
	protected ListProperty<PBoxCell> PlayProperty = new SimpleListProperty<>();
	protected ListProperty<BBoxCell> BookingProperty = new SimpleListProperty<>();
	
	Member currentUser = new Member(null, null, null, null, null, null, null);
	
	public void setmember(String id){
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
	         
	 		welcome.setText("Welcome back "+currentUser.name.toString()+" !\t\t Account Balance :"+ currentUser.balance.toString());
	 		
	 		data2.addAll(currentUser.getbookings());
			ArrayList<BBoxCell> temp3 = new ArrayList<>();
	 		for(int i=0; i< currentUser.getbookings().size();i++){
	 			Booking ab = currentUser.getbookings().get(i);
	 			Play movie = ab.getmovie();
	 			Show show = ab.getshow();
	 			temp3.add(new BBoxCell(ab.getid(), movie.name.toString(), show.screenname.toString(), show.time.toString(), ab.getprice(), currentUser.id));
	 		}
	 		
	 		bookinglist.itemsProperty().bind(BookingProperty);
			BookingProperty.set(FXCollections.observableArrayList(temp3));
	 		
	 		//Play Data
			
			FileInputStream playIn = new FileInputStream("Play.ser");
	         ObjectInputStream pin = new ObjectInputStream(playIn); 
	         playlist = (ArrayList<Play>) pin.readObject();
	         pin.close();
	         playIn.close();
	         System.out.println("size --> "+playlist.size());
	         if(playlist.size()>0){
					data.addAll(playlist);
					ArrayList<PBoxCell> temp2 = new ArrayList<>();
					for(int i=0; i< playlist.size();i++){
						if(playlist.get(i) instanceof LocalPlay){
							temp2.add(new PBoxCell(playlist.get(i).id, playlist.get(i).name, playlist.get(i).Description, playlist.get(i).price, playlist.get(i).getshowno(),"Local", "> GO", false,currentUser.id.toString()));
						}else{
							temp2.add(new PBoxCell(playlist.get(i).id, playlist.get(i).name, playlist.get(i).Description, playlist.get(i).price, playlist.get(i).getshowno(),"International", "> GO", false, currentUser.id.toString()));
						}
						
					}
					PlayList.itemsProperty().bind(PlayProperty);
					PlayProperty.set(FXCollections.observableArrayList(temp2));
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
	

	public void deletebooking(String bookingid) {
		try{
			  
	         FileInputStream fileIn = new FileInputStream("Theatre.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn); 
	         userlist = (ArrayList<Member>) in.readObject();
	         in.close();
	         fileIn.close();
	         
	         for(int i=0; i< userlist.size();i++){
		    	  if(currentUser.id.toString().equals(userlist.get(i).id)){
		    		  ArrayList<Booking> oldbooking = userlist.get(i).bookings;
		    		  ArrayList<Booking> newbooking = new ArrayList<>();
		    		  int k=0;
		    		  for(int j=0; j< oldbooking.size();j++){
		    			  if(!oldbooking.get(j).getid().equals(bookingid)){
		    				  Booking temp = oldbooking.get(j);
		    				  temp.setid(""+k);
		    				  k++;
		    				  newbooking.add(temp);
		    			  }
		    		  }
		    		  userlist.get(i).bookings = newbooking;
		    		  
		    	  }
	         }
	         
	         
	         FileOutputStream fileOut = new FileOutputStream("Theatre.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(userlist);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /Theatre.ser");

	       
	 		
	         
	         
	      }catch(IOException i){
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c){
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
		
		
	}
	
	@FXML
	public void initialize() throws EOFException{
			
	}
}
