package team273.robot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotTankFactory extends Robot {

	public RobotTankFactory(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		if (rc.isCoreReady() && rc.getTeamOre() >= 250) {
			Direction direction = directions[rand.nextInt(8)];
			if (rc.getTeamOre() > 250) {
				RobotType type = RobotType.TANK;
				try {
					trySpawn(direction, type);
				} catch (GameActionException e) {
					System.out.println("GameActionException encountered on trySpawn() in RobotTankFactory");
					e.printStackTrace();
				}
			}
		}
	}
}