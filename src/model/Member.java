package model;

import java.util.ArrayList;

public class Member extends User {
	public ArrayList<Booking> bookings;
	public Plan membership;
	public String balance;
	
	public Member(ArrayList<Booking> book, Plan plan, String id, String name , String username, String password, String age){
		super(id, name,username,password, age);
		this.bookings = book;
		this.membership = plan;
		this.balance = "0";
		this.bookings = new ArrayList<Booking>();
	}
	
	public void setmembership(String key) {
		// TODO Auto-generated method stub
		if(key.toString().equals(new String("1"))){
			System.out.println("key value 1");
			membership = new Standard("Standard", "0");
		}else if(key.toString().equals(new String("2"))){
			System.out.println("key value 2");
			membership = new Premium("Standard", "500");
		}else if(key.toString().equals(new String("3"))){
			System.out.println("key value 3");
			membership = new Deluxe("Deluxe", "2000");
		}else if(key.toString().equals(new String("4"))){
			System.out.println("key value 4");
			membership = new Elite("Elite", "10000");
		}
		
		System.out.println("key value data = "+key);
	}
	
	public String getplan(){
		String temp = "no plan";
		if(this.membership instanceof Standard){
			temp = "Standard";
		}else if(this.membership instanceof Premium){
			temp= "Premium";
		}else if(this.membership instanceof Deluxe){
			temp = "Deluxe";
		}else if(this.membership instanceof Elite){
			temp="Elite";
		}
		return temp;
	}
	
	public ArrayList<Booking> getbookings(){
		return this.bookings;
	}
	
	public void addbooking(Booking booking){
		booking.id = ""+this.bookings.size();
		this.bookings.add(booking);
	}
	
	public void addbalance(double addamount){
		double b = Double.parseDouble(this.balance) + addamount;
		this.balance = ""+b;
	}

}
