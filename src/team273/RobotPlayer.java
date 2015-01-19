package team273;

import team273.robot.Robot;
import battlecode.common.RobotController;

public class RobotPlayer {
	public static void run(RobotController rc) {
		Robot robot = Robot.factory(rc);
		robot.run();
	}
}
