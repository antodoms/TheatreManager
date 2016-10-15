package controller;

import java.io.EOFException;
import java.io.File;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Member;
import model.Plan;
import model.User;

public class MemberController {
	
	
	ArrayList<Member> userlist = new ArrayList<Member> ();
	
	@FXML
	private Pane commonPane;
	@FXML
	private TextField userName;
	@FXML
	private TextField userAge;
	@FXML
	private TextField userLogin;
	@FXML
	private PasswordField userPass;
	@FXML
	private ChoiceBox<KeyValuePair> selectplan;
	@FXML
	private TextField reference;
	
	
	public void addUser(ActionEvent event){
		Member newUser = new Member(null, null,null,userName.getText(),userLogin.getText(),userPass.getText(), userAge.getText());
		
		  try{
			  
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
	         }else{
	        	 userlist = new ArrayList<Member>();
	         }
	      }catch(IOException i){
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c){
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	      
	      
		  newUser.setmembership(selectplan.getValue().getKey().toString());
	      System.out.println("key stored = "+selectplan.getValue().getKey());
		  System.out.println("size: "+ userlist.size());
	      newUser.setid(""+userlist.size());
	      
	      userlist.add(newUser);
	      
	      for(int i=0; i< userlist.size();i++){
	    	  if(userlist.get(i).id.equals(reference.getText())){
	    		  userlist.get(i).addbalance(50);
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
		selectplan.getItems().add(new KeyValuePair("1", "Standard"));
		selectplan.getItems().add(new KeyValuePair("2", "Premium"));
		selectplan.getItems().add(new KeyValuePair("3", "Deluxe"));
		selectplan.getItems().add(new KeyValuePair("4", "Elite"));
	}
}
