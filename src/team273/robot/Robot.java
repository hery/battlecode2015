package team273.robot;

import java.util.Random;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import battlecode.common.Team;

public class Robot {
	public static final Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};

	protected RobotController rc;
	protected Random rand;

	protected int myRange;
	protected Team myTeam;
	protected RobotInfo[] myRobots;

	protected MapLocation enemyLoc;
	protected Team enemyTeam;

	protected Direction lastDirection;

	public Robot(RobotController rc) {
		this.rc = rc;
		rand = new Random(rc.getID());

		myRange = rc.getType().attackRadiusSquared;
		myTeam = rc.getTeam();

		enemyLoc = rc.senseEnemyHQLocation();
		enemyTeam = myTeam.opponent();
	}

	public static Robot factory(RobotController rc) {
		switch (rc.getType()) {
			case HQ:
				return new RobotHQ(rc);
			case TOWER:
				return new RobotTower(rc);
			case BASHER:
				return new RobotBasher(rc);
			case SOLDIER:
				return new RobotSoldier(rc);
			case BEAVER:
				return new RobotBeaver(rc);
			case BARRACKS:
				return new RobotBarracks(rc);
			case AEROSPACELAB:
				return new RobotAerospaceLab(rc);
			case COMMANDER:
				return new RobotCommander(rc);
			case COMPUTER:
				return new RobotComputer(rc);
			case DRONE:
				return new RobotDrone(rc);
			case HANDWASHSTATION:
				return new RobotHandwashStation(rc);
			case HELIPAD:
				return new RobotHelipad(rc);
			case MINER:
				return new RobotMiner(rc);
			case MINERFACTORY:
				return new RobotMinerFactory(rc);
			case MISSILE:
				return new RobotMissile(rc);
			case SUPPLYDEPOT:
				return new RobotSupplyDepot(rc);
			case TANK:
				return new RobotTank(rc);
			case TANKFACTORY:
				return new RobotTankFactory(rc);
			case TECHNOLOGYINSTITUTE:
				return new RobotTechnologyInstitute(rc);
			case TRAININGFIELD:
				return new RobotTrainingField(rc);
			default:
				return null;
		}
	}

	protected void doTurn() {}

	public void run() {
		while (true) {
			try {
				rc.setIndicatorString(0, "This is an indicator string.");
				rc.setIndicatorString(1, "I am a " + rc.getType());
			} catch (Exception e) {
				System.out.println("Unexpected exception setting indicator string");
				e.printStackTrace();
				continue;
			}

			try {
				doTurn();
			} catch (Exception e) {
				System.out.println("Unexpected exception in doTurn()");
				e.printStackTrace();
				continue;
			}

			rc.yield();
		}
	}

	// This method will attack an enemy in sight, if there is one
	protected void attackSomething() throws GameActionException {
		RobotInfo[] enemies = rc.senseNearbyRobots(myRange, enemyTeam);
		if (enemies.length > 0) {
			rc.attackLocation(enemies[0].location);
		}
	}

	// This method will attempt to move in Direction d (or as close to it as possible)
	protected void tryMove(Direction d) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2};
		int dirint = directionToInt(d);
		boolean blocked = false;
		while (offsetIndex < 5 && !rc.canMove(directions[(dirint+offsets[offsetIndex]+8)%8])) {
			offsetIndex++;
		}
		if (offsetIndex < 5) {
			rc.move(directions[(dirint+offsets[offsetIndex]+8)%8]);
		}
	}

	// This method will attempt to spawn in the given direction (or as close to it as possible)
	protected void trySpawn(Direction d, RobotType type) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2,3,-3,4};
		int dirint = directionToInt(d);
		boolean blocked = false;
		while (offsetIndex < 8 && !rc.canSpawn(directions[(dirint+offsets[offsetIndex]+8)%8], type)) {
			offsetIndex++;
		}
		if (offsetIndex < 8) {
			rc.spawn(directions[(dirint+offsets[offsetIndex]+8)%8], type);
		}
	}

	// This method will attempt to build in the given direction (or as close to it as possible)
	protected void tryBuild(Direction d, RobotType type) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2,3,-3,4};
		int dirint = directionToInt(d);
		boolean blocked = false;
		while (offsetIndex < 8 && !rc.canMove(directions[(dirint+offsets[offsetIndex]+8)%8])) {
			offsetIndex++;
		}
		if (offsetIndex < 8) {
			rc.build(directions[(dirint+offsets[offsetIndex]+8)%8], type);
		}
	}

	static int directionToInt(Direction d) {
		switch(d) {
			case NORTH:
				return 0;
			case NORTH_EAST:
				return 1;
			case EAST:
				return 2;
			case SOUTH_EAST:
				return 3;
			case SOUTH:
				return 4;
			case SOUTH_WEST:
				return 5;
			case WEST:
				return 6;
			case NORTH_WEST:
				return 7;
			default:
				return -1;
		}
	}
}