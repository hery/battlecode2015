package team273.robot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class RobotSoldier extends Robot {

	public RobotSoldier(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		if (rc.isWeaponReady()) {
			try {
				attackSomething();
			} catch (GameActionException e) {

				e.printStackTrace();
			}
		}
		if (rc.isCoreReady()) {
			int fate = rand.nextInt(1000);
			Direction direction;
			if (fate < 800) {
				direction = directions[rand.nextInt(8)];
			} else {
				direction = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
			}
			try {
				tryMove(direction);
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on tryMove() in RobotSoldier");
				e.printStackTrace();
			}
		}
	}
}