package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {
  CANSparkMax claw;
  Timer time = new Timer();
  boolean clawIsOpen = false;

  public ClawSubsystem() {
    initMotor();
  }

  @Override
  public void periodic() {
    if (clawIsOpen) {
      periodicClaw();
    }
  }

  @Override
  public void simulationPeriodic() {

  }

  private void initMotor() {
    claw = new CANSparkMax(11,MotorType.kBrushless);
    claw.setInverted(false);
  }

  private void periodicClaw() {

    if (time.get() == 0) time.start();
    
    claw.set(-0.3);
    SmartDashboard.putNumber("Timer", time.get());
    SmartDashboard.putBoolean("Garra", true);
    // if (time.get() > 2) {
    //   SmartDashboard.putNumber("Timer", time.get());
    //   claw.set(0);
    //   SmartDashboard.putBoolean("Garra", false);
    //   clawIsOpen = false;
    // }

  }

  public void timeRestart() {
    time.stop();
    time.reset();
  }

  public void clawActivate() {
    clawIsOpen = true;
    time.start();
  }

  public void clawDesactivate() {
    clawIsOpen = false;
    claw.set(0);
    timeRestart();
  }

}
