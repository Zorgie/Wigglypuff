package WigglyPuff;

import java.awt.Color;
import java.util.Random;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * BestTest - a robot by (your name here)
 */
public class WigglyPuff extends AdvancedRobot {

	private long then = System.currentTimeMillis();
	private double findingClose = 0.5;
	private double shootThresh = 200;
	private double closest = Integer.MAX_VALUE;
	private int moveDir = 1;
	private int gunDir = 1;
	private double otherEnergy = 100;
	private Random rng = new Random();

	/**
	 * run: BestTest's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.red, Color.blue, Color.green); // body,gun,radar
		setTurnGunRight(99999);

		// Robot main loop
	}

	
	
	public void onScannedRobot(ScannedRobotEvent e) {
		if(findingClose > 0){
			double leNice = e.getDistance();
			if(leNice < closest){
				closest = leNice;
				if(leNice < shootThresh){
					fire(3);
				}
			}
			long now = System.currentTimeMillis();
			findingClose-=(double)(now-then)/1000;
			return;
		}
		
		if(rng.nextInt(15) == 0){
			findingClose = 0.5;
		}
		
		// Funky movements
		setTurnRight(e.getBearing() + 75 + (rng.nextInt(30))
				* moveDir);

		// If the bot has small energy drop,
		// assume it fired
		double changeInEnergy = otherEnergy - e.getEnergy();
		// Shots leech 0-3 energy from yourself, monitor this!
		if (changeInEnergy > 0 && changeInEnergy <= 3) {
			// Dodge!
			moveDir = -moveDir;
			setAhead(99999 * moveDir);
		}
		// When a bot is spotted,
		// sweep the gun and radar
		gunDir = -gunDir;
		setTurnGunRight(99999 * gunDir);

		// Fire directly at target
		if(e.getDistance() < shootThresh){
			fire(3);
		}
		// Track the energy level
		otherEnergy = e.getEnergy();
	}

}