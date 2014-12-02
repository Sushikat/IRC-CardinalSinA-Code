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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    RobotDrive chassis = new RobotDrive(1, 2);
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    Gyro gyro = new Gyro(1);
    /**
     * This function is called once each time the robot enters autonomous mode.
     * There are lots of while loops and it might look complicated,
     * but you are repeating something several times, so it makes sense.
     */
    public void autonomous() {
        final double Kp = 0.03; //Proportionality Constant for turning (Google it)
        int autoCount = 0; // Seconds counter
        double angle = gyro.getAngle(); //The angle from home as measured by the gyro
        int turns = 0; // The number of turns the robot has made (needs to make 4 i think)
        while(turns < 3){ // Put in while loop to count number of times to turn
            autoCount = 0;//Reset secs counter
            while(autoCount < 3){ //Drive straight for 3 secs
                while(isEnabled() && isAutonomous()){
                    // Will switch these later
                    chassis.drive(-.5, -angle*Kp);
                    angle = gyro.getAngle(); //Fetch the angle for driving straight
                }
                Timer.delay(1.0); // Let one second of doing ^that pass
                autoCount++; // Increment seconds counter
            }
            angle = gyro.getAngle() - 90; // Turn 90 (not sure if left or right)
            turns++; // Increment turn count
        }
        chassis.drive(0.0, 0.0); // Stop at the end
    }

    /**
     * This function is called once each time the robot enters operator control.
     * This is filler I pulled off the FRC website
     */
    public void operatorControl() {
        chassis.setSafetyEnabled(true);
        while(isOperatorControl() && isEnabled()){
            chassis.tankDrive(leftStick, rightStick);
            Timer.delay(0.01);
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
