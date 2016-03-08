package edu.byu.cs.superasteroids.game;

import android.graphics.PointF;

/**
 * A simple class with static values for keeping track of user input
 */
public class InputManager {

	public static InputManager INSTANCE = new InputManager();

	/**
	 * This value will be null if there is no current move point, A PointF object otherwise
	 */
	public PointF movePoint = null;

	/**
	 * This value will be false if fire hasn't been pressed, true otherwise
	 */
	public boolean firePressed = false;


}
