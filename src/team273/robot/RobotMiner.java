package team273.robot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotMiner extends Robot {

	public RobotMiner(RobotController rc) {
		super(rc);
	}

	@Override
	protected void doTurn() {
			int fate = rand.nextInt(1000);
			if (!rc.isCoreReady()) {
				return;
			}

			if (fate < 500) {
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