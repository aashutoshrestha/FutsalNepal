package app.blackspring.com.futsalnepal.model.futsal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FutsalData{

	@SerializedName("success")
	private boolean success;

	@SerializedName("futsal_list")
	private List<FutsalListItem> futsalList;

	@SerializedName("message")
	private String message;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setFutsalList(List<FutsalListItem> futsalList){
		this.futsalList = futsalList;
	}

	public List<FutsalListItem> getFutsalList(){
		return futsalList;
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
			"FutsalData{" + 
			"success = '" + success + '\'' + 
			",futsal_list = '" + futsalList + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}