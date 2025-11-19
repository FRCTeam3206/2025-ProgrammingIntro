package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BallLauncherConstants;

public class BallLauncher extends SubsystemBase {
    private final WPI_VictorSPX m_Launcher = new WPI_VictorSPX(BallLauncherConstants.kLauncherMotor);
    public BallLauncher() {
      
    }
    public void launch() {
        m_Launcher.set(0.25);
    }

    public Command launchCommand() {
        return this.run(() -> launch());
    }

}
