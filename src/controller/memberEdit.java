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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Member;

public class memberEdit {
	ArrayList<Member> userlist = new ArrayList<Member> ();
	public Member currentUser;
	@FXML private Pane commonPane;
	@FXML private Label userid;
	@FXML private Label username;
	@FXML private Label userbalance;
	@FXML private Label userusername;
	@FXML private Label userage;
	@FXML private Label userplan;
	
	@FXML private TextField newbalance;
	@FXML private ChoiceBox<KeyValuePair> newplan;
	
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
	         
	         userid.setText(currentUser.id.toString());
	 		username.setText(currentUser.name.toString());
	 		userbalance.setText(currentUser.balance.toString());
	 		userusername.setText(currentUser.username.toString());
	 		userage.setText(currentUser.age.toString());
	 		userplan.setText(currentUser.getplan().toString());
	 		
	      }catch(IOException i){
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c){
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
		
	}
	
	public void addBalance(ActionEvent event){
		try{ 
	         FileInputStream fileIn = new FileInputStream("Theatre.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn); 
	         userlist = (ArrayList<Member>) in.readObject();
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
	      
	      for(int i=0; i< userlist.size();i++){
	    	  if(currentUser.id.equals(userlist.get(i).id)){
	    		  double addamount = Double.parseDouble(newbalance.getText());
	    		 userlist.get(i).addbalance(addamount);
	    	  }
	      }
	      
			try
		      {
		         FileOutputStream fileOut = new FileOutputStream("Theatre.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(userlist);
		         out.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in /Theatre.ser");   
		      }catch(IOException i)
		      {
		          i.printStackTrace();
		      }
		      
		      
		      try {
		    	  	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editmember.fxml"));
					Pane newpane = loader.load();
					Scene scene = new Scene(newpane);
					Stage primaryStage = (Stage) commonPane.getScene().getWindow();
					
					memberEdit controller = (memberEdit) loader.getController();
     				controller.setmember(currentUser.id.toString());
					
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		
	}
	
	public void changePlan(ActionEvent event){
		try{ 
	         FileInputStream fileIn = new FileInputStream("Theatre.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn); 
	         userlist = (ArrayList<Member>) in.readObject();
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
	      
	      for(int i=0; i< userlist.size();i++){
	    	  if(currentUser.id.equals(userlist.get(i).id)){
	    		 userlist.get(i).setmembership(newplan.getValue().getKey().toString());
	    	  }
	      }
	      
			try
		      {
		         FileOutputStream fileOut = new FileOutputStream("Theatre.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(userlist);
		         out.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in /Theatre.ser");   
		      }catch(IOException i)
		      {
		          i.printStackTrace();
		      }
		      
		      
			try {
	    	  	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editmember.fxml"));
				Pane newpane = loader.load();
				Scene scene = new Scene(newpane);
				Stage primaryStage = (Stage) commonPane.getScene().getWindow();
				
				memberEdit controller = (memberEdit) loader.getController();
 				controller.setmember(currentUser.id.toString());
				
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
	
	@FXML
	public void initialize() {
		newplan.getItems().add(new KeyValuePair("1", "Standard"));
		newplan.getItems().add(new KeyValuePair("2", "Premium"));
		newplan.getItems().add(new KeyValuePair("3", "Deluxe"));
		newplan.getItems().add(new KeyValuePair("4", "Elite"));
		
		
		
	}
	
}
