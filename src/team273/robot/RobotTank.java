package team273.robot;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotTank extends Robot {

	public RobotTank(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		if (Clock.getRoundNum() > ATTACK_THRESHOLD) {
			super.doTurn();
			return;
		}

		if (rc.isWeaponReady()) {
			try {
				attackSomething();
			} catch (GameActionException e) {
				e.printStackTrace();
			}
		}
		if (rc.isCoreReady()) {
			Direction direction = directions[rand.nextInt(8)];
			try {
				tryMove(direction);
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on tryMove() in RobotTank");
				e.printStackTrace();
			}
		}
	}
}