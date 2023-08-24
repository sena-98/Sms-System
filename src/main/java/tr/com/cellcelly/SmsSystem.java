package tr.com.cellcelly;


import com.mashape.unirest.http.exceptions.UnirestException;

public interface SmsSystem {
	
	 boolean connectToMW();
	 int checkBalance(String gsmNo) throws UnirestException;
	 boolean sendSMS(String gsmNo,int balance);

}
