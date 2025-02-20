package application;

import java.sql.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Text; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class database {
	
	public static String inputfName = "";
	public static String inputlName = "";
	public static String inputUser = "";
	public static String inputPass = "";
	public static String inputAddr = "";
	public static String inputPhone = "";
	public static String inputAge = "";
	public static String inputEmail = "";
	
	public static String domain = "";
	
	public static String all = "";
	public static String person = "";
	
	public static String username = "root";
	public static String password = "sqlpass";
	public static String url = "jdbc:mysql://localhost:3306/courtsystem";

	
	//String number = email.substring(email.lastIndexOf("@") + 1);
	
	public static void delete(String str)
	{
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			
			// delete data from reservation table if it exists (first)
			// delete data from directory table if it exists
			//Beginning of query
				
			PreparedStatement preparedStatement = 
			connection.prepareStatement("if exists "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?)");
	
			/*
			String first = fname.substring(0, 1).toUpperCase() + fname.substring(1);
			String last = lname.substring(0, 1).toUpperCase() + lname.substring(1);
			
			preparedStatement.setString(1, first);
			preparedStatement.setString(2, last);
			preparedStatement.setString(3, age);
			preparedStatement.setString(4, addr);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, u);
			preparedStatement.setString(8, p);
			
			*/
			//Executing Query
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	//str should = variable name in database if specific or 0 if everything
	public static void getData(String str)
	{
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			//directory
			if(str.equals("0"))
			{
				// Beginning of query
				PreparedStatement preparedStatement = 
						connection.prepareStatement("SELECT * FROM directory");
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					String fname = resultSet.getString("firstName");
					String lname = resultSet.getString("lastName");
					String age = resultSet.getString("age");
					String addr = resultSet.getString("address");
					String phone = resultSet.getString("phone");
					String email = resultSet.getString("email") + ".com";
					String u = resultSet.getString("username");
					String p = resultSet.getString("pword");
					
					all = all + fname + " " + lname + "  " + age + "  " + addr + "  " + 
					phone + "  " + email + "\n";
					
				}
			// display personal information
			} else {
				// Beginning of query
				PreparedStatement preparedStatement = 
						connection.prepareStatement("SELECT ? FROM directory");
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				String data = resultSet.getString(str);
				
				all = all + data;
			}
			
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	public static void nAccount(String fname, String lname, String age, String addr, 
			String phone, String email, String u, String p) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			
			//Beginning of query
			PreparedStatement preparedStatement = 
					connection.prepareStatement("insert into directory "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?)");
			
			String first = fname.substring(0, 1).toUpperCase() + fname.substring(1);
			String last = lname.substring(0, 1).toUpperCase() + lname.substring(1);
			
			preparedStatement.setString(1, first);
			preparedStatement.setString(2, last);
			preparedStatement.setString(3, age);
			preparedStatement.setString(4, addr);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, u);
			preparedStatement.setString(8, p);
			
			//Executing Query
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public static void addData(String fname, String lname, String age, String addr, 
			String phone, String email, String u, String p) {
		
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			
			//Beginning of query
			PreparedStatement preparedStatement = 
					connection.prepareStatement("insert into directory "
							+ "values(?,?,?,?,?,?,?,?)");
			
			//Makes the first letters of the first and last name capital
			String first = fname.substring(0, 1).toUpperCase() + fname.substring(1);
			String last = lname.substring(0, 1).toUpperCase() + lname.substring(1);
			
			preparedStatement.setString(1, first);
			preparedStatement.setString(2, last);
			preparedStatement.setString(3, age);
			preparedStatement.setString(4, addr);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, u);
			preparedStatement.setString(8, p);
			
			
			//Executing Query
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	//not done yet
	public static void editData(String fname, String lname, String age, String addr, 
			String phone, String u, String p) {
		
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			
			//Beginning of query
			PreparedStatement preparedStatement = 
					connection.prepareStatement("update directory set age = ? "
							+ "where name = ?");
			
			String first = fname.substring(0, 1).toUpperCase() + fname.substring(1);
			String last = lname.substring(0, 1).toUpperCase() + lname.substring(1);
			

			preparedStatement.setString(1, first);
			preparedStatement.setString(2, last);
			preparedStatement.setString(3, age);
			preparedStatement.setString(4, addr);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, u);
			preparedStatement.setString(7, p);
			
			
			//Executing Query
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	public static boolean login(String user, String pass)
	{
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			// Beginning of query
			
			PreparedStatement preparedStatement = 
					connection.prepareStatement("SELECT * FROM directory");
						
			ResultSet resultSet = preparedStatement.executeQuery();
						
			while(resultSet.next()) {
				String uname = resultSet.getString("username");
				String pword = resultSet.getString("pword");
				
				if(uname.equals(user) && pword.equals(pass))
				{
					String em = resultSet.getString("email");
					
					domain = em.substring(em.lastIndexOf("@") + 1);
					
					person = resultSet.getString("firstName") + " " + 
							resultSet.getString("lastName");
					
					return true;
				}
			}
			
			return false;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	public static void display(int n) {

		Stage window = new Stage();
		Text text = new Text();
		VBox layout = new VBox(10);
		Scene scene = new Scene(layout);
		
		Button submit = new Button("Submit and exit");
		Button exit = new Button("Exit");
		exit.setOnAction(e -> window.close());

		TextField fieldfName = new TextField();
		fieldfName.setPromptText("Enter first name");
		//makes it so that you can see the prompt if hovering over it
		fieldfName.setFocusTraversable(false);
		
		TextField fieldlName = new TextField();
		fieldlName.setPromptText("Enter last name");
		fieldlName.setFocusTraversable(false);
		
		TextField fieldAge = new TextField();
		fieldAge.setPromptText("Enter age");
		//makes it so that you can see the prompt if hovering over it
		fieldAge.setFocusTraversable(false);
		
		TextField fieldEmail = new TextField();
		fieldEmail.setPromptText("Enter e-mail");
		fieldEmail.setFocusTraversable(false);
		
		TextField fieldUsername = new TextField();
		fieldUsername.setPromptText("Enter username");
		fieldUsername.setFocusTraversable(false);
		
		TextField fieldPass = new TextField();
		fieldPass.setPromptText("Enter password");
		fieldPass.setFocusTraversable(false);
		
		TextField fieldAddr = new TextField();
		fieldAddr.setPromptText("Enter address");
		fieldAddr.setFocusTraversable(false);
		
		TextField fieldPhone = new TextField();
		fieldPhone.setPromptText("Enter phone number");
		fieldPhone.setFocusTraversable(false);
		
		layout.getChildren().add(text);
		
		//makes it go that you can't leave the new window until
		//it's taken care of
		window.initModality(Modality.APPLICATION_MODAL);
		
		window.setMinWidth(400);
		window.setMinHeight(400);
		
		if(n == 1)
		{
			//for administrator
			window.setTitle("Add new entry in Database");
			
			submit.setOnAction(e -> {
				inputfName = fieldfName.getText();
				inputlName = fieldlName.getText();
				inputUser = fieldUsername.getText();
				inputPass = fieldPass.getText();
				inputAddr = fieldAddr.getText();
				inputEmail = fieldEmail.getText();
				inputPhone = fieldPhone.getText();
				inputAge = fieldAge.getText();
						
				addData(inputfName, inputlName, inputAge, inputAddr, inputPhone, 
						inputEmail, inputUser, inputPass);
				
				
						
				window.close();
						
				});

			layout.getChildren().addAll(fieldfName, fieldlName, fieldAge, fieldAddr, 
					fieldPhone, fieldEmail, fieldUsername, fieldPass, submit);
			
		} else if (n == 2) {
			window.setTitle("Showing Database");
			
			all = "";
			
			getData("0");
			text.setText(all);
			//text.setX(50); 
		    //text.setY(50); 
			
			//Error here
			layout.getChildren().addAll(exit);
			
		} else if (n == 3) {
			window.setTitle("Make changes");
			
			submit.setOnAction(e -> {
				inputfName = fieldfName.getText();
				inputlName = fieldlName.getText();
				inputUser = fieldUsername.getText();
				inputPass = fieldPass.getText();
				inputAddr = fieldAddr.getText();
				inputEmail = fieldEmail.getText();
				inputPhone = fieldPhone.getText();
				inputAge = fieldAge.getText();
						
				editData(inputfName, inputlName, inputAge, inputAddr, inputPhone, 
						inputEmail, inputPass);
						
				window.close();
						
				});
			
			layout.getChildren().addAll(fieldfName, fieldlName, fieldAge, fieldAddr, 
					fieldPhone, fieldUsername, fieldPass);
			
		} else if (n == 4) {
			window.setTitle("New Account");
			
			submit.setOnAction(e -> {
				inputfName = fieldfName.getText();
				inputlName = fieldlName.getText();
				inputUser = fieldUsername.getText();
				inputPass = fieldPass.getText();
				inputAddr = fieldAddr.getText();
				inputEmail = fieldEmail.getText();
				inputEmail = inputEmail.substring(0, inputEmail.length() - 4);
				inputPhone = fieldPhone.getText();
				inputAge = fieldAge.getText();
				
				nAccount(inputfName, inputlName, inputAge, inputAddr, inputPhone, 
						inputEmail, inputUser, inputPass);
						
				window.close();
						
				});

			layout.getChildren().addAll(fieldfName, fieldlName, fieldAge, fieldAddr, 
					fieldPhone, fieldEmail, fieldUsername, fieldPass, submit);
		}
				
		window.setScene(scene);
		
		//displays window and then before returning to previous screen,
		//waits for it to be closed
		window.showAndWait();
	}
	
}