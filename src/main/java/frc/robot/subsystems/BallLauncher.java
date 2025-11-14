package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BallLauncherConstants;

public class BallLauncher extends SubsystemBase {
    private final WPI_VictorSPX m_Launcher = new WPI_VictorSPX(BallLauncherConstants.kLauncherMotor);
}
