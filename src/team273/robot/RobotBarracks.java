package team273.robot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotBarracks extends Robot {

	public RobotBarracks(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		int fate = rand.nextInt(10000);

		// get information broadcasted by the HQ
		int numBeavers;
		int numSoldiers;
		int numBashers;
		try {
			numBeavers = rc.readBroadcast(0);
			numSoldiers = rc.readBroadcast(1);
			numBashers = rc.readBroadcast(2);
		} catch (GameActionException e) {
			System.err.println("GameActionException encountered in readBroadcast() in RobotBarracks");
			e.printStackTrace();
			return;
		}

		if (rc.isCoreReady() && rc.getTeamOre() >= 60 && fate < Math.pow(1.2,15-numSoldiers-numBashers+numBeavers)*10000) {
			Direction direction = directions[rand.nextInt(8)];
			RobotType type;
			if (rc.getTeamOre() > 80 && fate % 2 == 0) {
				type = RobotType.BASHER;
			} else {
				type = RobotType.SOLDIER;
			}
			try {
				trySpawn(direction, type);
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on trySpawn() in RobotBarracks");
				e.printStackTrace();
			}
		}
	}
}