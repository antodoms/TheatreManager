package model;


public class DiscountBooking extends Booking{

	Plan plan;
	
	public DiscountBooking(Plan plan) {
		super();
		this.plan = plan;
	}
	
	public void setplan(Plan plan){
		this.plan = plan;
	}
	
	public void setprice(int price){
		double finalprice = price;
		System.out.println("wowwww =-===- >>>   Awesoe");
		if((this.plan instanceof Premium) && (this.movie instanceof LocalPlay)){
			finalprice = finalprice - finalprice * 0.05;
		}else if((this.plan instanceof Premium) && (this.movie instanceof ForeignPlay)){
			finalprice = finalprice - finalprice * 0.1;
		}else if((this.plan instanceof Deluxe) && (this.movie instanceof LocalPlay)){
			finalprice = finalprice - finalprice * 0.1;
		}else if((this.plan instanceof Deluxe) && (this.movie instanceof ForeignPlay)){
			finalprice = finalprice - finalprice * 0.15;
		}else if((this.plan instanceof Elite) && (this.movie instanceof LocalPlay)){
			finalprice = finalprice - finalprice * 0.15;
		}else if((this.plan instanceof Elite) && (this.movie instanceof ForeignPlay)){
			finalprice = finalprice - finalprice * 0.20;
		}
		
		this.price = ""+finalprice;
	}
	
	
}
