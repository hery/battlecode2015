package team273.robot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;

public class RobotBasher extends Robot {

	public RobotBasher(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		RobotInfo[] adjacentEnemies = rc.senseNearbyRobots(2, enemyTeam);

		// BASHERs attack automatically, so let's just move around mostly randomly
		if (rc.isCoreReady()) {
			int fate = rand.nextInt(1000);
			Direction direction;
			if (fate < 800) {
				direction = directions[rand.nextInt(8)];
			} else {
				direction = rc.getLocation().directionTo(enemyLoc);
			}
			try {
				tryMove(direction);
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on tryMove() in RobotBasher");
				e.printStackTrace();
			}
		}
	}
}