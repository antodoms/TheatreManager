package model;

import java.io.Serializable;
import java.util.Arrays;

public class Show implements Serializable{
	public String id;
	public String screenname;
	public String time;
	public String showtype;
	
	public String seats[] = new String[50];
	
	public Show(String name, String time, String type){
		this.screenname = name;
		this.time = time;
		this.showtype = type;
	}
}
