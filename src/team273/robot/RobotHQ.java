package team273.robot;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class RobotHQ extends Robot {

	public RobotHQ(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
		int fate = rand.nextInt(10000);
		myRobots = rc.senseNearbyRobots(999999, myTeam);
		int numSoldiers = 0;
		int numBashers = 0;
		int numBeavers = 0;
		int numBarracks = 0;
		for (RobotInfo r : myRobots) {
			RobotType type = r.type;
			if (type == RobotType.SOLDIER) {
				numSoldiers++;
			} else if (type == RobotType.BASHER) {
				numBashers++;
			} else if (type == RobotType.BEAVER) {
				numBeavers++;
			} else if (type == RobotType.BARRACKS) {
				numBarracks++;
			}
		}

		try {
			rc.broadcast(0, numBeavers);
			rc.broadcast(1, numSoldiers);
			rc.broadcast(2, numBashers);
			rc.broadcast(100, numBarracks);
		} catch (GameActionException e) {
			System.out.println("GameActionException encountered on broadcast() in RobotHQ");
			e.printStackTrace();
		}

		if (rc.isWeaponReady()) {
			try {
				attackSomething();
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on attackSomething() in RobotHQ");
				e.printStackTrace();
			}
		}

		int numberOfBeavers = 0;
		try {
			numberOfBeavers = rc.readBroadcast(0);
		} catch (GameActionException e) {
			System.out.println("HQ couldn't read broadcast!");
		}
		if (rc.isCoreReady() && rc.getTeamOre() >= 100 && numberOfBeavers <= 10) {
			try {
				trySpawn(directions[rand.nextInt(8)], RobotType.BEAVER);
			} catch (GameActionException e) {
				System.out.println("GameActionException encountered on trySpawn() in RobotHQ");
				e.printStackTrace();
			}
		}
	}
}