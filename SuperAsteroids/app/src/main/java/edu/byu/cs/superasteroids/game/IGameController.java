package edu.byu.cs.superasteroids.game;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 *
 */
public interface IGameController
{

    /**
     * Updates the game controller. The game engine will call this function 60 times a second
     * once it enters the game loop.
     * @param input This is used to check if the user has interacted with the device at all.
     *              See the InputManager class for details
     */
	void update(double elaspsedTime, InputManager input);

    /**
     * Loads content such as image and sounds files and other data into the game. The GameActivty will
     * call this once right before entering the game engine enters the game loop. The ShipBuilding
     * activity calls this function in its onCreate() function.
     * @param content An instance of the content manager. This should be used to load images and sound
     *                files.
     */
	void loadContent(ContentManager content);

    /**
     * Unloads content from the game. The GameActivity will call this function after the game engine
     * exits the game loop. The ShipBuildingActivity will call this function after the "Start Game"
     * button has been pressed.
     * @param content An instance of the content manager. This should be used to unload image and
     *                sound files.
     */
    void unloadContent(ContentManager content);

    /**
     * Draws the game controller. This function will be 60 times a second.
     * @param drawingHelper Use this to draw the various pieces on the screen, or to get helpful
     *                      information about the devices screen (width/height).
     */
	void draw(DrawingHelper drawingHelper);

}
