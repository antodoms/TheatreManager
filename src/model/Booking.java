package model;

import java.io.Serializable;

public class Booking implements Serializable {
	public String id;
	public String price;
	public Play movie;
	public Show shows;
	public int seats[]= new int[5];

	public Booking() {
	}
	
	public void setplay(Play play){
		this.movie = play;
	}
	
	public void setshows(Show shows){
		this.shows = shows;
	}
	
	public void setid(String id){
		this.id = id;
	}
	
	public String getid(){
		return this.id;
	}
	public String getprice(){
		return this.price;
	}
	
	public Play getmovie(){
		return this.movie;
	}
	
	public Show getshow(){
		return this.shows;
	}
	
	public void setprice(double d, Plan plan, Play play) {
		double finalprice = d;
		System.out.println("wowwww =-===- >>>   Awesoe");
		if((plan instanceof Premium) && (play instanceof LocalPlay)){
			finalprice = finalprice - finalprice * 0.05;
		}else if((plan instanceof Premium) && (play instanceof ForeignPlay)){
			finalprice = finalprice - finalprice * 0.1;
		}else if((plan instanceof Deluxe) && (play instanceof LocalPlay)){
			finalprice = finalprice - finalprice * 0.1;
		}else if((plan instanceof Deluxe) && (play instanceof ForeignPlay)){
			finalprice = finalprice - finalprice * 0.15;
		}else if((plan instanceof Elite) && (play instanceof LocalPlay)){
			finalprice = finalprice - finalprice * 0.15;
		}else if((plan instanceof Elite) && (play instanceof ForeignPlay)){
			finalprice = finalprice - finalprice * 0.20;
		}
		
		this.price = ""+finalprice;
	}
	
	
	public void toggleseats(int index){
		if(seats[index]==0){
			seats[index]=1;
		}else{
			seats[index]=0;
		}
	}
	
	public int getseats(int index){
		return seats[index];
	}

}