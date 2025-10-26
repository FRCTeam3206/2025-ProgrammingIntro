// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import java.util.function.DoubleSupplier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Drive extends SubsystemBase {
  // The motors on the left side of the drive.
  private final VictorSPX m_leftLeader = new VictorSPX(DriveConstants.kLeftMotor1ID);
  private final VictorSPX m_leftFollower = new VictorSPX(DriveConstants.kLeftMotor2ID);

  // The motors on the right side of the drive.
  private final VictorSPX m_rightLeader = new VictorSPX(DriveConstants.kRightMotor1ID);
  private final VictorSPX m_rightFollower = new VictorSPX(DriveConstants.kRightMotor2ID);

  // The robot's drive
  // @NotLogged // Would duplicate motor data, there's no point sending it twice
  private final DifferentialDrive m_drive =
      new DifferentialDrive(
        (value) -> m_leftLeader.set(ControlMode.PercentOutput, value),
        (value) -> m_rightLeader.set(ControlMode.PercentOutput, value)
      );

  /** Creates a new Drive subsystem. */
  public Drive() {
    SendableRegistry.addChild(m_drive, m_leftLeader);
    SendableRegistry.addChild(m_drive, m_rightLeader);

    m_leftFollower.follow(m_leftLeader);
    m_rightFollower.follow(m_rightLeader);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightLeader.setInverted(true);
  }

  /**
   * Returns a command that drives the robot with arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public Command arcadeDriveCommand(DoubleSupplier fwd, DoubleSupplier rot) {
    // A split-stick arcade command, with forward/backward controlled by the left
    // hand, and turning controlled by the right.
    return run(() -> m_drive.arcadeDrive(fwd.getAsDouble(), rot.getAsDouble()))
        .withName("arcadeDrive");
  }

}
