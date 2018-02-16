package app.blackspring.com.futsalnepal.model.login;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginData{

	@SerializedName("success")
	private boolean success;

	@SerializedName("user_details")
	private List<UserDetailsItem> userDetails;

	@SerializedName("message")
	private String message;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setUserDetails(List<UserDetailsItem> userDetails){
		this.userDetails = userDetails;
	}

	public List<UserDetailsItem> getUserDetails(){
		return userDetails;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"LoginData{" + 
			"success = '" + success + '\'' + 
			",user_details = '" + userDetails + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}