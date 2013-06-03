package WigglyPuff;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;
// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * BestTest - a robot by (your name here)
 */
public class WigglyPuff extends Robot
{
	/**
	 * run: BestTest's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}

  /*
   * Returns true f within fireing distance*/
  private boolean withingDistance() {
    if (e.getDistance() < 300) {
      return true;
    }
    return false;
  }

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		if (withingDistance() && getEnergy() > 50) {
		  fire(3);
    } else {
      fire(1);
	  }
  }

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
