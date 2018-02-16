package app.blackspring.com.futsalnepal.model.futsal;

import com.google.gson.annotations.SerializedName;

public class FutsalListItem{

	@SerializedName("image")
	private String image;

	@SerializedName("address")
	private String address;

	@SerializedName("distance")
	private String distance;

	@SerializedName("lattitude")
	private String lattitude;

	@SerializedName("closing_time")
	private String closingTime;

	@SerializedName("opening_time")
	private String openingTime;

	@SerializedName("rating")
	private double rating;

	@SerializedName("name")
	private String name;

	@SerializedName("phone_number")
	private String phoneNumber;

	@SerializedName("longitude")
	private String longitude;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setDistance(String distance){
		this.distance = distance;
	}

	public String getDistance(){
		return distance;
	}

	public void setLattitude(String lattitude){
		this.lattitude = lattitude;
	}

	public String getLattitude(){
		return lattitude;
	}

	public void setClosingTime(String closingTime){
		this.closingTime = closingTime;
	}

	public String getClosingTime(){
		return closingTime;
	}

	public void setOpeningTime(String openingTime){
		this.openingTime = openingTime;
	}

	public String getOpeningTime(){
		return openingTime;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"FutsalListItem{" + 
			"image = '" + image + '\'' + 
			",address = '" + address + '\'' + 
			",distance = '" + distance + '\'' + 
			",lattitude = '" + lattitude + '\'' + 
			",closing_time = '" + closingTime + '\'' + 
			",opening_time = '" + openingTime + '\'' + 
			",rating = '" + rating + '\'' + 
			",name = '" + name + '\'' + 
			",phone_number = '" + phoneNumber + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}