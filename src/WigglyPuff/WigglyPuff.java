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
  private double targetAngle;
  private int tracking = 0;
	
  /**
	 * run: BestTest's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here
    targetAngle = 0;

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
      if (!isTracking())
  			turnGunRight(360);
			back(100);
      if (!isTracking())
			  turnGunRight(360);
		}
	}

  private boolean isTracking() {
    return false;
  }

  private boolean veryClose(ScannedRobotEvent e) {
    if (e.getDistance() < 100) {
      return true;
    }
    return false;
  }

  /*
   * Returns true f within fireing distance*/
  private boolean withinLongestDistance(ScannedRobotEvent e) {
    if (e.getDistance() < 300) {
      return true;
    }
    return false;
  }

  private boolean hasEnergy() {
    if (getEnergy() > 50) {
      return true;
    }
    return false;
  }

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
    
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

    targetAngle = bearingFromGun;
    
    if (Math.abs(targetAngle) <= 3) {
      turnGunRight(targetAngle);
      
      if (hasEnergy()) {
        if (veryClose(e)) {
            fire(3);
        } else if (withinLongestDistance(e)) {
          fire(0.4);
        } else {
			ahead(200);
		}
      }
    }
    
     //else if (withinLongestDistance(e) && hasEnergy()) {
      //fire(1);
	  //}
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
