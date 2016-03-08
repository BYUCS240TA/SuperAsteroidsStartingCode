package edu.byu.cs.superasteroids.main_menu;

import edu.byu.cs.superasteroids.base.IController;
import edu.byu.cs.superasteroids.content.ContentManager;

public interface IMainMenuController extends IController {

    /**
     * The MainActivity calls this function when the "quick play" button is pressed.
     */
    void onQuickPlayPressed();

    /**
     * The MainActivty calls this functions to load the model from the database.
     */
    void initializeModelFromDataBase();
}
