/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
    private final Joystick m_stick = new Joystick(0);
    private final Timer m_timer = new Timer();
    private final Compressor m_compressor = new Compressor(0);
    private final DoubleSolenoid m_pneuSolenoid = new DoubleSolenoid(1, 2);

    public class JoystickMap {
        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
        public static final int Y = 4;
        public static final int LB = 5;
        public static final int RB = 6;
        public static final int BACK = 7;
        public static final int START = 8;
        public static final int L3 = 9;
        public static final int R3 = 10;
        public static final int LEFT_TRIGGER = 2;
        public static final int RIGHT_TRIGGER = 3;
        public static final int LEFT_X = 0;
        public static final int LEFT_Y = 1;
        public static final int RIGHT_X = 4;
        public static final int RIGHT_Y = 5;
    };

/**
 * This function is run when the robot is first started up and should be
 * used for any initialization code.
 */
@Override
public void robotInit() {
    }

/**
 * This function is run once each time the robot enters autonomous mode.
 */
@Override
public void autonomousInit() {
        m_timer.reset();
        m_timer.start();
    }

/**
 * This function is called periodically during autonomous.
 */
@Override
public void autonomousPeriodic() {
    // Drive for 2 seconds
}

/**
 * This function is called once each time the robot enters teleoperated mode.
 */
@Override
public void teleopInit() {
}

/**
 * This function is called periodically during teleoperated mode.
 */
@Override
public void teleopPeriodic() {
    // read the inputs
    boolean needCompressorOn = m_stick.getRawButton(JoystickMap.Y);
    boolean pneuExtend = m_stick.getRawButton(JoystickMap.A);
    boolean pneuRetract = m_stick.getRawButton(JoystickMap.B);

    // operate the compressor
    m_compressor.setClosedLoopControl(needCompressorOn);

    // operate the pneumatic cylinder solenoid
    DoubleSolenoid.Value sol = pneuExtend? DoubleSolenoid.Value.kForward
                             : pneuRetract? DoubleSolenoid.Value.kReverse
                             : DoubleSolenoid.Value.kOff;
    m_pneuSolenoid.set(sol);
}

/**
 * This function is called periodically during test mode.
 */
@Override
public void testPeriodic() {
}
}
