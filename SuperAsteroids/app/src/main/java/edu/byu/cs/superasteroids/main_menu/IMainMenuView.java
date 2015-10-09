package edu.byu.cs.superasteroids.main_menu;

import android.os.Parcelable;

import edu.byu.cs.superasteroids.base.IView;

public interface IMainMenuView extends IView{
    /**
     * Instructs the ShipBuildingView to start the game with the provided ship.
     * @param ship The ship to be used in the game.
     */
    void startGame(Parcelable ship);
}
