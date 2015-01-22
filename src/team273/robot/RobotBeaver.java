package team273.robot;

import battlecode.common.Clock;
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
		if (Clock.getRoundNum() > ATTACK_THRESHOLD) {
			super.doTurn();
		}

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
			if (fate < 200 && rc.getTeamOre() >= 500) {
				try {
					RobotType type = RobotType.BARRACKS;
					if (rc.hasBuildRequirements(RobotType.MINERFACTORY) && Clock.getRoundNum() < 50) {
						type = RobotType.MINERFACTORY;
					}
					if (rc.hasBuildRequirements(RobotType.AEROSPACELAB) && fate > 0 && fate < 25 ) {
						type = RobotType.AEROSPACELAB;
					}
					if (rc.hasBuildRequirements(RobotType.TANKFACTORY) && fate >= 25 && fate < 75) {
						type = RobotType.TANKFACTORY;
					}
					if (rc.hasBuildRequirements(RobotType.HELIPAD) && fate >= 75 && fate < 150) {
						type = RobotType.HELIPAD;
					}
					tryBuild(directions[rand.nextInt(8)], type);
				} catch (GameActionException e) {
					System.out.println("GameActionException encountered on tryBuild() in RobotBeaver");
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
				Direction direction = directions[rand.nextInt(8)];
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