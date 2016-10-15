package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import helper.SBoxCell;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Booking;
import model.DiscountBooking;
import model.Member;
import model.Play;
import model.Show;
import model.Standard;
import model.StandardBooking;

public class bookingpage {
	
	Member currentUser = new Member(null, null, null, null, null, null, null);
	ArrayList<Member> userlist = new ArrayList<Member> ();
	ArrayList<Play> playlist = new ArrayList<Play> ();
	public Play currentPlay = new Play(null, null, null);
	public Show currentShow = new Show(null, null, null);
	public Booking currentBooking = new Booking();
	public int noofseatsbooked = 0;
	@FXML private Pane commonPane;
	@FXML private Label totalbalance;
	@FXML private Label totalprice;
	@FXML private Label errormessage;
	@FXML private Button b1;
	@FXML private Button b2;
	@FXML private Button b3;
	@FXML private Button b4;
	@FXML private Button b5;

	public void setmember(String id) {
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
	         
	      }catch(IOException i){
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c){
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
		
	}
	
	public void setPlay(String playid, String showid) {
		// TODO Auto-generated method stub
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
			         
			         for(int i=0; i< playlist.size();i++){
			        	 if(playlist.get(i).id.toString().equals(playid.toString())){
			        		 currentPlay = playlist.get(i);
			        		 for(int j=0; j< currentPlay.getshowlist().size();j++){
			        			 if(currentPlay.getshowlist().get(j).id.toString().equals(showid.toString())){
			        				 currentShow = currentPlay.getshowlist().get(j);
			        			 }
			        		 }
			        	 }
			         }
		
				     currentBooking.setplay(currentPlay);
				     currentBooking.setshows(currentShow);
				     currentBooking.setprice(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked, currentUser.membership, currentPlay);
			         totalbalance.setText("Total Balance: "+currentUser.balance.toString());
			         totalprice.setText("Total Cost: "+Double.parseDouble(currentPlay.price.toString())*noofseatsbooked);
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
	
	public void button1(ActionEvent event){
		currentBooking.toggleseats(0);
		if(currentBooking.getseats(0)==0){
			b1.setOpacity(1);
			noofseatsbooked--;
		}else{
			b1.setOpacity(0.5);
			noofseatsbooked++;
		}
		totalbalance.setText("Total Balance: "+currentUser.balance.toString());
		currentBooking.setprice(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked, currentUser.membership, currentPlay);
        totalprice.setText("Total Price: "+(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked)+"\tFinal Cost: "+currentBooking.price);
		}
	
	public void button2(ActionEvent event){
		currentBooking.toggleseats(1);
		if(currentBooking.getseats(1)==0){
			b2.setOpacity(1);
			noofseatsbooked--;
		}else{
			b2.setOpacity(0.5);
			noofseatsbooked++;
		}
		totalbalance.setText("Total Balance: "+currentUser.balance.toString());
		currentBooking.setprice(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked, currentUser.membership, currentPlay);
        totalprice.setText("Total Price: "+(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked)+"\tFinal Cost: "+currentBooking.price);
		}
	public void button3(ActionEvent event){
		currentBooking.toggleseats(2);
		if(currentBooking.getseats(2)==0){
			b3.setOpacity(1);
			noofseatsbooked--;
		}else{
			b3.setOpacity(0.5);
			noofseatsbooked++;
		}
		totalbalance.setText("Total Balance: "+currentUser.balance.toString());
		currentBooking.setprice(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked, currentUser.membership, currentPlay);
        totalprice.setText("Total Price: "+(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked)+"\tFinal Cost: "+currentBooking.price);
		}
	public void button4(ActionEvent event){
		currentBooking.toggleseats(3);
		if(currentBooking.getseats(3)==0){
			b4.setOpacity(1);
			noofseatsbooked--;
		}else{
			b4.setOpacity(0.5);
			noofseatsbooked++;
		}
		totalbalance.setText("Total Balance: "+currentUser.balance.toString());
		currentBooking.setprice(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked, currentUser.membership, currentPlay);
        totalprice.setText("Total Price: "+(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked)+"\tFinal Cost: "+currentBooking.price);
		}
	public void button5(ActionEvent event){
		currentBooking.toggleseats(4);
		if(currentBooking.getseats(4)==0){
			b5.setOpacity(1);
			noofseatsbooked--;
		}else{
			b5.setOpacity(0.5);
			noofseatsbooked++;
		}
		totalbalance.setText("Total Balance: "+currentUser.balance.toString());
		currentBooking.setprice(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked, currentUser.membership, currentPlay);
        totalprice.setText("Total Price: "+(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked)+"\tFinal Cost: "+currentBooking.price);
	}
	public void purchasetickets(ActionEvent event){
		if(isbalanceavailable()){
			if(isselected()){
				try{
					  
			         FileInputStream fileIn = new FileInputStream("Theatre.ser");
			         ObjectInputStream in = new ObjectInputStream(fileIn); 
			         userlist = (ArrayList<Member>) in.readObject();
			         in.close();
			         fileIn.close();
			         
			         
			         currentBooking.setprice(Double.parseDouble(currentPlay.price.toString())*noofseatsbooked, currentUser.membership, currentPlay);
			         for(int i=0; i< userlist.size();i++){
				    	  if(currentUser.id.toString().equals(userlist.get(i).id)){
				    		  
				    		  userlist.get(i).balance = ""+(Double.parseDouble(userlist.get(i).balance) - Double.parseDouble(currentBooking.price));
				    		  userlist.get(i).addbooking(currentBooking);
				    	  }
				      }
			         
			         FileOutputStream fileOut = new FileOutputStream("Theatre.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(userlist);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in /Theatre.ser");
			         
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
						
			         
			      }catch(IOException i){
			         i.printStackTrace();
			         return;
			      }catch(ClassNotFoundException c){
			         System.out.println("Employee class not found");
			         c.printStackTrace();
			         return;
			      }
			}else{
				errormessage.setText("Please selected atleast 1 seat to continue booking !");
			}
		}else{
			errormessage.setText("Please topup your account to continue booking !");
		}
	}
	
	public boolean isselected(){
		boolean checked = false;
		for(int i=0; i< currentBooking.seats.length ;i++){
			if(currentBooking.seats[i] == 1){
				checked = true;
			}
		}
		
		return checked;
	}
	
	public boolean isbalanceavailable(){
		boolean checked= false;
		double userbalance = Double.parseDouble(currentUser.balance);
		double bookingprice = Double.parseDouble(currentPlay.price.toString())*noofseatsbooked;
		if(userbalance >= bookingprice){
			checked=true;
		}
		
		return checked;
	}

}
