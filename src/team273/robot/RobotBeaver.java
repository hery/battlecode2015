package team273.robot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotBeaver extends Robot {

	public RobotBeaver(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		if (rc.isWeaponReady()) {
			try {
				attackSomething();
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on attackSomething() in RobotBeaver");
				e.printStackTrace();
			}
		}
		if (rc.isCoreReady()) {
			int fate = rand.nextInt(1000);
			if (fate < 8 && rc.getTeamOre() >= 300) {
				try {
					tryBuild(directions[rand.nextInt(8)], RobotType.BARRACKS);
				} catch (GameActionException e) {
					System.out.println("GameActionException encountered on tryBuild() of barracks in RobotBeaver");
					e.printStackTrace();
				}
			} else if (fate < 600) {
				try {
					rc.mine();
				} catch (GameActionException e) {
					System.out.println("GameActionException encountered on mine() in RobotBeaver");
					e.printStackTrace();
				}
			} else {
				Direction direction;
				if (fate < 900) {
					direction = directions[rand.nextInt(8)];
				} else {
					direction = rc.senseHQLocation().directionTo(rc.getLocation());
				}
				try {
					tryMove(direction);
				} catch (GameActionException e) {
					System.out.println("GameActionException encountered on tryMove() in RobotBeaver");
					e.printStackTrace();
				}
			}
		}
	}
}