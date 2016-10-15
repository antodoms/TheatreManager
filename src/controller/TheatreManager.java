package controller;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Member;
import model.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class TheatreManager extends Application {
	
	public ArrayList<User> userlist;
	private String id=null;
	@FXML
	private Label isConnected;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	
	@Override
	public void start(Stage primaryStage) {
		/*
		File yourFile = new File("Theatre.ser");
		if(!yourFile.exists()) {
		    try {
				yourFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		try {
			FileOutputStream oFile = new FileOutputStream(yourFile, false);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		*/
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Login (ActionEvent event) {
		try {
			//System.out.println("user:"+txtUsername.getText()+"  pass:"+txtPassword.getText());
			if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("pass")){
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/view/adminDashboard.fxml"));
				adminDashboard userController = (adminDashboard) loader.getController();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			else if(isLogin(txtUsername.getText(), txtPassword.getText())){
				((Node)event.getSource()).getScene().getWindow().hide();
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/memberDashboard.fxml")); 
 				Stage primaryStage = new Stage();
        		Pane newpane = loader.load();
 				Scene scene = new Scene(newpane);
 				
 				memberdashboard userController = (memberdashboard) loader.getController();
				userController.setmember(id.toString());
				scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
				
			}else {
				isConnected.setText("Username and password is not correct");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isLogin(String username, String Password){
		FileInputStream fileIn;
		Boolean check = false;
		try {
			fileIn = new FileInputStream("Theatre.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn); 
	        userlist = (ArrayList<User>) in.readObject();
	        in.close();
	        fileIn.close();
	        
	        for(int i=0; i< userlist.size(); i++){
	        	Member abc = (Member) userlist.get(i);
	        	if(abc.username.equals(username) && abc.password.equals(Password)){
	        		id = abc.id;
	        		check = true;
	        	}
	        }
	        
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return check;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
