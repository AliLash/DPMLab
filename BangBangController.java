import java.util.*;

import lejos.nxt.*;

public class BangBangController implements UltrasonicController{
	private final int bandCenter, bandwith;
	private final int motorLow, motorHigh;
	//private final int motorStraight = 200;
	private final int motorStraight = 150;
	private final NXTRegulatedMotor leftMotor = Motor.A, rightMotor = Motor.C;
	private int distance;
	private int currentLeftSpeed;
	
	
	public BangBangController(int bandCenter, int bandwith, int motorLow, int motorHigh) {
		//Default Constructor
		this.bandCenter = bandCenter;
		this.bandwith = bandwith;
		this.motorLow = motorLow;
		this.motorHigh = motorHigh;
		leftMotor.setSpeed(motorStraight);
		rightMotor.setSpeed(motorStraight);
		leftMotor.forward();
		rightMotor.forward();
		currentLeftSpeed = 0;
	}
	
	@Override
	public void processUSData(int distance) {
		this.distance = distance;
		if (distance >= 250){
			try { Thread.sleep(400); } catch(Exception e){}
			if (distance >= 250){
				rightMotor.setSpeed(motorHigh);
				leftMotor.setSpeed(motorStraight);
				}
			if (!(distance >= 250)){
				leftMotor.setSpeed(motorStraight);
				rightMotor.setSpeed(motorStraight);
			}
						
			}
			
		if (distance < 250){
		
		
		if((distance - bandCenter) > bandwith || (bandCenter - distance) > bandwith) {
			if (distance < bandCenter){
				//if (distance < 5){
					//leftMotor.setSpeed(400);
					//rightMotor.setSpeed(50);
					
				//}
				//if(distance > 5){
				leftMotor.setSpeed(motorHigh);
				rightMotor.setSpeed(motorLow);
				}
			//}
		
			else if(distance > bandCenter){
				rightMotor.setSpeed(motorHigh);
				leftMotor.setSpeed(motorStraight);
				//leftMotor.setSpeed(motorLow);
				}
				
			
		}
		else {
			leftMotor.setSpeed(motorStraight);
			rightMotor.setSpeed(motorStraight);
			
			}
		 }
		
	
	
	}

	
	@Override
	public int readUSDistance() {
		
		return this.distance;
	}
}
