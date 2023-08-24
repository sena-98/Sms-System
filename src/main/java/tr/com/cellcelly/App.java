package tr.com.cellcelly;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
	
	
	private static SmsSystem mySystem = new CellCellySmsSystem();
		
    private static Scene scene;

  

    public static void main(String[] args) {
        launch();
    }



	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane root  = new GridPane();
		root.setPadding(new Insets(30));
		root.setVgap(20);
		root.setHgap(20);
		
		
		// GridPane tasarımı oluşturuluyor
		Text title = new Text("CellCelly SMS");
		title.setFont(Font.font(20));
		Text caption = new Text ("Message");
		//Text caption2 = new Text ("To");
		TextField smsInput = new TextField();
		//TextField smsTo = new TextField();
		Button btnSend = new Button("Send");

		Text title2 = new Text("CellCelly NO");
		title.setFont(Font.font(20));
		Text caption2 = new Text ("Telefon");
		//Text caption2 = new Text ("To");
		TextField smsInput2 = new TextField();
		//TextField smsTo = new TextField();
		//Button btnSend2 = new Button("Telefon numarası");


     Text title3 = new Text("");


		
		//Nesneleri gridpane'e yerleştir.
		root.add(title, 1, 1);
		root.add(caption, 1, 2);
		root.add(smsInput, 2, 2);
		
		//root.add(caption2, 1, 3);
		//root.add(smsTo, 2, 3);
		
		root.add(btnSend, 2, 4);



		root.add(title2, 4, 1);
		root.add(caption2, 4, 2);
		root.add(smsInput2, 4, 2);
		//root.add(btnSend2, 4, 4);

		root.add(title3, 4, 4);

		btnSend.setOnAction(e->{
			
			if(!smsInput.getText().equals("KALAN 5555")) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Hatalı mesaj girişi");
				alert.setContentText("Girilen mesaj : KALAN 5555 şeklinde olmalıdır.");
				alert.show();
				
				return;
				
				
			}


			String gsmNo = smsInput2.getText();
			CellCellySmsSystem cell=new CellCellySmsSystem();
			try {
				int sms=cell.checkBalance(gsmNo);
				title3.setText(String.valueOf(sms));
			} catch (UnirestException ex) {
				throw new RuntimeException(ex);
			}
			boolean connectionResult = App.mySystem.connectToMW();
			
			if(connectionResult) {
				try {
					int balance = 	App.mySystem.checkBalance(gsmNo);
				} catch (UnirestException ex) {
					throw new RuntimeException(ex);
				}
				//	 System.out.println("Kalan miktar : " + balance);
			// App.mySystem.sendSMS(gsmNo, balance);
			}
			
			
		});
		
		
		Scene scene  = new Scene(root);
		Stage  stage = new Stage();
		
		stage.setScene(scene);
		stage.show();
	
			
	}
    
}