/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Logger;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Subsystem_Pneumatics extends Subsystem {
  private static final Logger log = new Logger(Subsystem_Pneumatics.class);

  public static class ConstructorArgs {
    public Solenoid m_middleUp;
    public Solenoid m_middleDown;
    public Solenoid m_floatUp;
    public Solenoid m_floatDown;
    public Solenoid m_massForward;
    public Solenoid m_massBack;
    public Compressor m_compressor;

  }

  public enum AXLE {
    MIDDLE, FLOAT
  }

  private Solenoid m_middleUp;
  private Solenoid m_middleDown;
  private Solenoid m_floatUp;
  private Solenoid m_floatDown;
  private Solenoid m_massForward;
  private Solenoid m_massBack;
  private Compressor m_compressor;

  public Subsystem_Pneumatics(ConstructorArgs args) {
    this.m_middleUp = args.m_middleUp;
    this.m_middleDown = args.m_middleDown;
    this.m_floatUp = args.m_floatUp;
    this.m_floatDown = args.m_floatDown;
    this.m_massForward = args.m_massForward;
    this.m_massBack = args.m_massBack;
    this.m_compressor = args.m_compressor;

    addChild(this.m_middleUp);
    addChild(this.m_middleDown);
    addChild(this.m_floatUp);
    addChild(this.m_floatDown);
    addChild(this.m_massForward);
    addChild(this.m_massBack);
    addChild(this.m_compressor);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void extendBothAxles() {
    log.debug("***extendBothAxles");
    extendAxle(AXLE.FLOAT);
    extendAxle(AXLE.MIDDLE);
  }

  public void retractBothAxles() {
    log.debug("***retractBothAxles");
    retractAxle(AXLE.MIDDLE);
    retractAxle(AXLE.FLOAT);
  }

  public void extendAxle(AXLE axle) {
    log.debug("***extendAxle(" + axle.name() + ")");
    if (axle == AXLE.FLOAT) {
      this.m_floatUp.set(false);
      this.m_floatDown.set(true);
    } else {
      this.m_middleUp.set(false);
      this.m_middleDown.set(true);
    }
  }

  public void retractAxle(AXLE axle) {
    log.debug("***retractAxle(" + axle.name() + ")");
    if (axle == AXLE.FLOAT) {
      this.m_floatDown.set(false);
      this.m_floatUp.set(true);
    } else {
      this.m_middleDown.set(false);
      this.m_middleUp.set(true);
    }
  }

  public void moveMassForward() {
    log.debug("***moveMassForward");
    this.m_massBack.set(false);
    this.m_massForward.set(true);
  }

  public void moveMassBack() {
    log.debug("***movMassBack");
    this.m_massForward.set(false);
    this.m_massBack.set(true);
  }

  public void startCompressor() {
    log.debug("***startCompressor");
    this.m_compressor.start();
  }

  public void stopCompressor() {
    log.debug("***stopCompressor");
    this.m_compressor.stop();
  }

  public static Subsystem_Pneumatics create() {
    ConstructorArgs args = new ConstructorArgs();
    args.m_middleUp = new Solenoid(RobotMap.solenoid_Module_Axle_MiddleUp, RobotMap.solenoid_Channel_Axle_MiddleUp);
    args.m_middleDown = new Solenoid(RobotMap.solenoid_Module_Axle_MiddleDown,
        RobotMap.solenoid_Channel_Axle_MiddleDown);
    args.m_floatUp = new Solenoid(RobotMap.solenoid_Module_Axle_FloatUp, RobotMap.solenoid_Channel_Axle_FloatUp);
    args.m_floatDown = new Solenoid(RobotMap.solenoid_Module_Axle_FloatDown, RobotMap.solenoid_Channel_Axle_FloatDown);
    args.m_massForward = new Solenoid(RobotMap.solenoid_Module_MassMover_Forward,
        RobotMap.solenoid_Channel_MassMover_Forward);
    args.m_massBack = new Solenoid(RobotMap.solenoid_Module_MassMover_Back, RobotMap.solenoid_Channel_MassMover_Back);
    args.m_compressor = new Compressor(RobotMap.compressor_Module);

    return new Subsystem_Pneumatics(args);
  }
}
