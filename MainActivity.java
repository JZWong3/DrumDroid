package com.example.juniordesign;
 
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
 
import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.content.DialogInterface;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
 
 
public class MainActivity extends Activity implements OnSeekBarChangeListener, OnClickListener{
    private static final int UDP_SERVER_PORT = 45786; //Port number
    private SeekBar bar;
    private TextView textUser;
    private TextView dataSent;
    private Button button, button3, button4;
    private String BeagleIP = "192.168.7.2";
    private String DesktopCSharp = ""; //IP Address for the desktop computer in lab
    //Send data to this desktop address. Receive on Desktop, then process data
    // and send out to the BeagleBone
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Progress status text view
        textUser = (TextView)findViewById(R.id.textView1);
        textUser.setText("Play The Drums!");
        
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
       
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);

         
        //new Thread(new Client()).start();
         
    }
     
    //This is the class that you will need in order to create a 
    //Separate thread for the user to run both the android 
    //Application and the client at the same time. 
    public class Client implements Runnable {
        //private final static String BeagleIP = "192.168.7.2";
         
        String udpMsg = "";
        //Log.d("udpMsg", udpMsg);
        byte[] sendData = new byte[1024];
        //Constructor for taking just the slider value
        public Client(int progress) {
            // TODO Auto-generated constructor stub
            udpMsg = String.valueOf(progress) + "\0";
            run();
        }
        //Constructor for determining which motor to use
        public Client(int progress, String value) {
            // TODO Auto-generated constructor stub
             
            //This will come in handy on the server side.
            //No real use on client side, other than setting
            //So the server can differentiate between the motors
            if(value.equals("X"))
            {
                 
                udpMsg = String.valueOf(progress) + "X" + "\0";
               // run();
            }
            else if (value.equals("Y"))
            {
                udpMsg = String.valueOf(progress) + "Y" + "\0";
                //run();
            }
            run();
        }
         
        //This is the function to run the UDP server
        //I call this function in my overloaded constructors
        @Override
        public void run() {
            try {
            // TODO Auto-generated method stub
            InetAddress serverAddr = InetAddress.getByName("192.168.0.23");
            //note that 192.168.0.23 is the static IP address we used for the Wi-Fi adapter
            
            //InetAddress serverAddr = InetAddress.getByName("192.168.7.2"); //Address of the BeagleBone

            DatagramSocket socket = new DatagramSocket();
            sendData = udpMsg.getBytes();
            //There is also an error with sending data through the DatagramPacket
             
             
            Log.d("IP", serverAddr.toString()); //This is printing out as /192.168.7.2
             
            Log.d("Test0", "Before new DatagramPacket\n");
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, serverAddr, 45678);
            Log.d("Test1.5", "After the new DatagramPacket\n");
             
            socket.send(packet); //This is where the exception is caught. Not sure why yet
             
            Log.d("Test1", "The program is in the runUDP\n");
            dataSent.setText("Data has been sent to the server!");
            }
            catch (Exception e)
            {
                Log.d("Test5", "Basic Exception", e);
            }
        }
         
    }
     
     
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()) {
        case R.id.button1:
        	textUser.setText("Drumroll? Fancy!");
            Drumroll();
            break;
        case R.id.button3:
        	textUser.setText("Lets see how good you are!");
        	manualPlay2();
            
            break;
        case R.id.button4:
        	textUser.setText("Lets see how good you are!");
        	manualPlay1();
            break;
        //Log.d("Button Click", "Got to onClick!"); //test
        }
    }
     

	public void Drumroll() {
			int x = 15;
	        int x1 = 5;
	        
	        int y = 60;
	        int y1 = 85;
	       
        for(int i=0;i<45;i++){
             
            //Start of motor one aka Motor X
            //Must create a separate thread in order to run the motors
            /****************************************************/
            new Thread(new Client(x, "X")).start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            new Thread(new Client(x1, "X")).start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //Don't sleep the thread in between because as one stick goes up
            //The other stick has to go down at the same time. 
            //Start of Motor two aka Motor Y
            /****************************************************/
            new Thread(new Client(y, "Y")).start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            new Thread(new Client(y1, "Y")).start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         
        }
    }

    public void manualPlay1() { // Left Drum Stick
        int x = 60;
        int x1 = 0;
       // textUser.setText("Lets see how good you are!");
        /****************************************************/
        new Thread(new Client(x, "X")).start();
        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new Thread(new Client(x1, "X")).start();
        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void manualPlay2() { // Right Drum Stick
    	//Fix right motor
        int y = 10;//40
        int y1 = 90; //90
        //textUser.setText("Lets see how good you are!");
        /****************************************************/
        new Thread(new Client(y, "Y")).start();
        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new Thread(new Client(y1, "Y")).start();
        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
     
     
     
 
     
}
