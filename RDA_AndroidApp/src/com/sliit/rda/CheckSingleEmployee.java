package com.sliit.rda;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckSingleEmployee  extends Activity {
	
	private Button btLoad;
	private TextView eName;
	private TextView eID;
	
	private boolean finishedProcess = false;
	private ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();
	private SoapObject result = null;

	private static String SOAP_ACTION = "http://tempuri.org/GetEmployeeByID";
	private static String NAMESPACE = "http://tempuri.org/";
	private static String METHOD_NAME = "GetEmployeeByID";
	private static String URL = "http://192.168.123.100:8080/Service.asmx?WSDL";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_employee);
		eName = (TextView) findViewById(R.id.eName);
		eID = (TextView) findViewById(R.id.eId);
		
		btLoad = (Button) findViewById(R.id.btLoad);
		btLoad.setOnClickListener(buttonSaveOnClickListener);
	}
	
	public void connectSOAP(String empid) {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("id", empid);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result = (SoapObject) envelope.bodyIn;
		progressBarStatus = progressBarStatus + 100;
		
	}

	
	Button.OnClickListener buttonSaveOnClickListener = new Button.OnClickListener() {
		public void onClick(View v) {
			progressBar = new ProgressDialog(v.getContext());
			progressBar.setCancelable(true);
			progressBar.setMessage("Retrieving data from RDA Web service...");
			progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressBar.setProgress(0);
			progressBar.setMax(100);
			progressBar.show();
			progressBarStatus = 0;
			
			new Thread(new Runnable() {
				public void run() {
					while (progressBarStatus < 100) {

						try {
							Thread.sleep(1000);
							
							String empid = eID.getText().toString();
							connectSOAP(empid);			
							finishedProcess = true;
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						progressBarHandler.post(new Runnable() {
							public void run() {
								progressBar.setProgress(progressBarStatus);
							}
						});
					}
					if (progressBarStatus >= 100) {
						
						try {
							Thread.sleep(2000);
							
							// UI running Thread !!
							runOnUiThread(new Runnable() {
							     public void run() {
							    		if(finishedProcess == true){
							    			try {
							    				if (result != null) {
							    					String ename = result.toString();
							    					if (ename == null) {
							    						AlertDialog alertDialog = new AlertDialog.Builder(
							    								CheckSingleEmployee.this).create();

							    						alertDialog.setTitle("RDA");
							    						alertDialog.setMessage("Invalid Emp ID, No data found!");

							    						alertDialog.setButton("OK",
							    								new DialogInterface.OnClickListener() {
							    									public void onClick(DialogInterface dialog,
							    											int which) {
							    									}
							    								});
							    						alertDialog.show();
							    						return;
							    					}
							    		
							    						eName.setText(ename);
							    					}
							    			} catch (Exception e) {
							    				e.printStackTrace();
							    			}
											finishedProcess = false;
										}
							    }
							});	
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						progressBar.dismiss();
					}
				}
			}).start();
		}
	};
}
