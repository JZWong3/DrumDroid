DrumDroid
=========

Description
------------
Using an Android application through UDP connection over Wi-Fi, the user is able to send packets to a set of servo motors that will move drum sticks in a certain motion to play the drums. 

DrumDroid is a Android application that interfaces with a BeagleBone Black running a UDP server. The android application sends UDP packets over WiFi to the BeagleBone's UDP server. The client (Android app) has 3 buttons; left stick, right stick and drumroll. For example, if the "Left Stick" button is pressed, it will send that data to the server and the server will interpret the request and turn a small server motor (that has a drumstick mounted on it) a certain amount of degrees to hit a snare drum. If the drumroll button is pressed, it will be placed in an approximate 10 second loop that will imitate a drumroll. 

Materials
---------
- BeagleBone Black (Running a LinuxOS build)
- 2 Small Servo Motors
- Drum Sticks x2
- Snare Drum
- N150 Wireless Adapter
- Android Phone

Notes
------
When using the BeagleBone Black, make sure to have wicd setup in order to use the N150 Wireless Adapter using "sudo apt-get install wicd". The Servo Motors are run on a PWM controller; built onto the BeagleBone. 



Created by Ainoghena Igetei, Louis Lo and Jonny Wong
