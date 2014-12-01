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
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    RobotDrive chassis = new RobotDrive(1, 2);
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    Gyro gyro = new Gyro(1);
    
    double Kp = 0.03;
    
    public void autonomous() {
        double angle = gyro.getAngle();
        while(isAutonomous() && isEnabled()){
            angle = gyro.getAngle();
            // Will switch these later
            chassis.drive(-2.0, -angle*Kp);
        }
        Timer.delay(2.0);
        angle = gyro.getAngle() - 90.0;
        while(isAutonomous() && isEnabled()){
            angle = gyro.getAngle();
            chassis.drive(-2.0, -angle * Kp);
        }
        Timer.delay(2.0);
        angle = gyro.getAngle() -90.0;
        while(isAutonomous() && isEnabled()){
            angle = gyro.getAngle();
            chassis.drive(-2.0, -angle * Kp);
        }
        Timer.delay(2.0);
        angle = gyro.getAngle() -90.0;
        while(isAutonomous() && isEnabled()){
            angle = gyro.getAngle();
            chassis.drive(-2.0, -angle * Kp);
        }
        
    }

    /**
     * This function is called once each time the robot enters operator control.
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
     */
    public void test() {
    
    }
}
