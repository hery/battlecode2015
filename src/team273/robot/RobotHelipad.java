package team273.robot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotHelipad extends Robot {

	public RobotHelipad(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		if (rc.isCoreReady() && rc.getTeamOre() >= 300) {
			Direction direction = directions[rand.nextInt(8)];
			RobotType type = RobotType.DRONE;
			try {
				trySpawn(direction, type);
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on trySpawn() in RobotHelipad");
				e.printStackTrace();
			}
		}
	}
}