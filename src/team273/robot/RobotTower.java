package team273.robot;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class RobotTower extends Robot {

	public RobotTower(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		if (rc.isWeaponReady()) {
			try {
				attackSomething();
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on attackSomething() in RobotTower");
				e.printStackTrace();
			}
		}
	}
}