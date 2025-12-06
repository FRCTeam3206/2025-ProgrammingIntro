package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BallLauncherConstants;

public class BallLauncher extends SubsystemBase {
    private final WPI_VictorSPX m_Launcher = new WPI_VictorSPX(BallLauncherConstants.kLauncherMotor);
    private final WPI_VictorSPX m_Feeder =  new WPI_VictorSPX(BallLauncherConstants.kFeederMotor);
    /*private final Encoder motorSpeed = new Encoder(BallLauncherConstants.kLauncherMotor, BallLauncherConstants.kLauncherMotor);
    public BallLauncher() {  
    }
    
    public void startUp() {        
        m_Launcher.set(0.1);
    }
    
    public void launch() {
        if (motorSpeed.getRate() > 0.1) {
            m_Launcher.set(0.7);
        }
        else {
            stop();
        }
    }

    public void stop(){
        m_Launcher.stopMotor();
    }
*/
    public Command fullLaunchCommand() {
        return this.run
        (() -> {m_Launcher.set(.8);
                m_Feeder.set(1.0);})
        
                        //.withTimeout(1.0)
                        /* .andThen(() -> {
                            m_Feeder.set(1.0);
                            m_Launcher.set(1.0);
                        })*/
                        
            .finallyDo(() -> {m_Launcher.stopMotor(); m_Feeder.stopMotor();})   ;

    }
    public Command halfLaunchCommand() {
        return this.run
        (() -> {m_Launcher.set(.65);
                m_Feeder.set(1.0);})
        
                        //.withTimeout(1.0)
                        /* .andThen(() -> {
                            m_Feeder.set(1.0);
                            m_Launcher.set(1.0);
                        })*/
                        
            .finallyDo(() -> {m_Launcher.stopMotor(); m_Feeder.stopMotor();})   ;

    }
}