package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Play implements Serializable {
	public String id;
	public String name;
	public String Description;
	public String price;
	
	public ArrayList<Show> showlist;
	
	public Play(String name, String description, String price) {
		this.name = name;
		this.Description = description;
		this.price = price;
		this.showlist = new ArrayList<Show>();
	}
	
	public void setid(String id){
		this.id = id;
	}
	
	public ArrayList<Show> getshowlist(){
		return this.showlist;
	}
	
	public void addshow(Show shows){
		this.showlist.add(shows);
	}
	
	public String getshowno(){
		System.out.println("showlist ---> "+this.showlist.size());
		String ab = ""+this.showlist.size();
		return ab;
	}

}
