/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;
/**
 *  @author Iris Buschelman
 *  Spirit Group's Autonomous code for Cardinal Sin A
 */

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    
    RobotDrive chassis = new RobotDrive(leftDrive, rightDrive);
    
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    
    Talon leftDrive = new Talon(1);
    Talon rightDrive = new Talon(2);
    Talon pickup = new Talon(3);
    
    Compressor mainCompressor = new Compressor(1, 1);
    
    DoubleSolenoid shifter = new DoubleSolenoid(5,6);
    DoubleSolenoid rightPickup = new DoubleSolenoid(3,4);
    DoubleSolenoid leftPickup = new DoubleSolenoid(1,2);
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        //turning to the right: chassis.drive(0.0,0.75);
        mainCompressor.start(); 
        leftPickup.set(DoubleSolenoid.Value.kReverse);
        rightPickup.set(DoubleSolenoid.Value.kReverse);
        shifter.set(DoubleSolenoid.Value.kReverse);
        int autoCount = 0;              //Turn 4x
        while(autoCount < 4) {
            chassis.drive(-0.5, 0.0);   //Drive forward @ half speed
            Timer.delay(3.0);           //Drive for 3 secs
            chassis.drive(0.0,0.75);    //Turn Right
            Timer.delay(0.125);          //Wait 1/8 secs
            autoCount++;                //Increment turn
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        mainCompressor.start();
        leftPickup.set(DoubleSolenoid.Value.kReverse);
        rightPickup.set(DoubleSolenoid.Value.kReverse);
        shifter.set(DoubleSolenoid.Value.kReverse);
        
        while(isOperatorControl() && isEnabled()){
            chassis.tankDrive(leftStick, rightStick);
            //Lower Pickup
            if(rightStick.getRawButton(1)){
                leftPickup.set(DoubleSolenoid.Value.kForward);
                rightPickup.set(DoubleSolenoid.Value.kForward);
            }
            //Shift up
            if(rightStick.getRawButton(5)){
                shifter.set(DoubleSolenoid.Value.kForward);
            }
            //Shift Down
            if(rightStick.getRawButton(6)){
                shifter.set(DoubleSolenoid.Value.kReverse);
            }
            //Raise Pickup
            if(rightStick.getRawButton(1)){
                leftPickup.set(DoubleSolenoid.Value.kReverse);
                rightPickup.set(DoubleSolenoid.Value.kReverse);
            }
            //Spit out
            if(rightStick.getRawButton(3)){
                pickup.set(1.0);
            }else{
                pickup.set(0.0);
            }
            //Pull in
            if(rightStick.getRawButton(4)){
                pickup.set(-1.0);
            }else{
                pickup.set(0.0);
            }
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     * Can go??? Test and see!
     */
    public void test() {
        chassis.drive(-0.5, 0.0);
    }
}
