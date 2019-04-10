/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Logger;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Subsystem_MiddleAxle extends Subsystem {
  public static final Logger log = new Logger(Subsystem_MiddleAxle.class);

  private Solenoid m_middleUp;
  private Solenoid m_middleDown;

  public Subsystem_MiddleAxle(Solenoid m_middleUp, Solenoid m_middleDown) {
    this.m_middleUp = m_middleUp;
    this.m_middleDown = m_middleDown;
    reset();
  }

  public void extend() {
    log.debug("*** middleAxle extend");
    pulseExtend(RobotMap.solenoidPulseTime_MiddleAxle_Down);
  }

  public void pulseExtend(double durationSeconds) {
    log.debug("*** middleAxle pulseExtend(" + String.valueOf(durationSeconds));
    m_middleUp.set(false);
    m_middleDown.setPulseDuration(durationSeconds);
    m_middleDown.startPulse();
  }

  public void retract() {
    log.debug("*** middleAxle retract");
    pulseRetract(RobotMap.solenoidPulseTime_MiddleAxle_Up);
  }

  public void pulseRetract(double durationSeconds) {
    log.debug("*** middleAxle pulseRetract(" + String.valueOf(durationSeconds));
    m_middleDown.set(false);
    m_middleUp.setPulseDuration(durationSeconds);
    m_middleUp.startPulse();
  }

  @Override
  public void initDefaultCommand() {
  }

  public void reset() {
    retract();
  }

  public static Subsystem_MiddleAxle create() {
    Solenoid m_middleUp = new Solenoid(RobotMap.solenoid_Module_Axle_MiddleUp, RobotMap.solenoid_Channel_Axle_MiddleUp);
    Solenoid m_middleDown = new Solenoid(RobotMap.solenoid_Module_Axle_MiddleDown,
        RobotMap.solenoid_Channel_Axle_MiddleDown);
    return new Subsystem_MiddleAxle(m_middleUp, m_middleDown);
  }
}
