package team273.robot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotMinerFactory extends Robot {

	public RobotMinerFactory(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		if (rc.isCoreReady() && rc.getTeamOre() >= 60) {
			Direction direction = directions[rand.nextInt(8)];
			RobotType type = RobotType.MINER;
			if (rc.getTeamOre() > 60) {
				try {
					trySpawn(direction, type);
				} catch (GameActionException e) {
					System.out.println("GameActionException encountered on trySpawn() in RobotMinerFactory");
					e.printStackTrace();
				}
			}
		}
	}
}