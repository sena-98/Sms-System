package tr.com.cellcelly;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CellCellySmsSystem implements SmsSystem {


	@Override
	public boolean connectToMW() {

		System.out.println("MiddleWare'e bağlanıyor....");
		try {

			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("MiddleWare'e bağlandı!");


		return true;

	}
	/*
	public int getMsisdn() throws UnirestException {
		HttpResponse<String> msisdn = Unirest.get("http://35.194.5.106:8080/api/balance/getuserbalance/" + getMSISDN())
				.header("accept", "application/json")
				.asString();
		return 0;
	}
*/
	/*
	public int getMsisdn() throws UnirestException {
		HttpResponse<String> msisdn = Unirest.get("http://35.194.5.106:8080/api/balance/getuserbalance/" + getMSISDN())
				.header("accept", "application/json")
				.asString();
		return 0;
	}

	 */


	@Override
	public int checkBalance(String gsmNo) throws UnirestException {

		System.out.println("Kalan SMS miktarı sorgulanıyor.." + gsmNo);
		HttpResponse<String> response = Unirest.get("http://35.194.5.106:8080/api/balance/getuserbalance/" + gsmNo)
				.header("accept", "application/json")
				.asString();
		String responseBody = response.getBody();

		try {
			// JSON dizisini ayrıştır
			JSONArray jsonArray = new JSONArray(responseBody);

			// İlk öğeyi seç
			JSONObject jsonObject = jsonArray.getJSONObject(0);

			// "sms" değerini al
			int smsValue = jsonObject.getInt("sms");

			// Sonucu yazdır
			System.out.println("SMS value: " + smsValue);
			return smsValue;
		} catch (Exception e) {
			e.printStackTrace();

		}
       return 0;



		//System.out.println("Response body: " + response.getBody());



	}


	@Override
	public boolean sendSMS(String gsmNo,int balance) {

		System.out.println( gsmNo + " sms gönderiliyor...");
		try {

			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println("Sms gönderme başarılı!");
		
		
		return true;

	}

}
