package edu.byu.cs.superasteroids.game;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import edu.byu.cs.superasteroids.R;
import edu.byu.cs.superasteroids.base.ActivityView;
import edu.byu.cs.superasteroids.content.AudioManagement;
import edu.byu.cs.superasteroids.content.ContentManager;

public class GameActivity extends ActivityView {

	private GameView gameView;
	private IGameController gameController;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// remove title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        this.setContentView(R.layout.game);

        //Create and add the GameView to the frame in the layout
        gameView = new GameView(this);
        FrameLayout frame = (FrameLayout)this.findViewById(R.id.game_frame);
        frame.addView(gameView);

        //TODO: Set the gameController to a new instance of your class the implements IGameController.
        //gameController = Instance of your game controller
        
        //Set the view's game controller and have it load content
        gameView.setGameController(gameController);
        gameController.loadContent(ContentManager.getInstance());
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {

        //If the player is initially pressing down or is dragging their finger
        if(e.getAction() == MotionEvent.ACTION_DOWN || e.getAction() == MotionEvent.ACTION_MOVE) {

            //Set the movepoint
            InputManager.INSTANCE.movePoint = new PointF(e.getX(), e.getY());

            //Assume we aren't firing
            InputManager.INSTANCE.firePressed = false;

            //If the player is initially pressing down, then fire
            if(e.getAction() == MotionEvent.ACTION_DOWN )
                InputManager.INSTANCE.firePressed = true;
            return true;
        }
        //If the player removed their finger, make sure the movepoint and firePressed are cleared
		else if(e.getAction() == MotionEvent.ACTION_UP) {
            InputManager.INSTANCE.movePoint = null;
            InputManager.INSTANCE.firePressed = false;
            return true;
        }
		
		return super.onTouchEvent(e);
	}

    @Override
    public void onPause() {
        super.onPause();
        gameView.pause();
        //Pause the music so it doesn't play when the application is covered by another screen.
        AudioManagement.getInstance().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        gameView.resume();
        AudioManagement.getInstance().onResume();
    }

    @Override
    public void onBackPressed() {
        if(gameController != null) {
            gameView.setDone(true);
            // Pause a little before we unload content to make sure
            // the game loop thread is no longer trying to draw
            try { Thread.sleep(100); } catch (InterruptedException e){}
            gameController.unloadContent(ContentManager.getInstance());
        }

        this.finish();
    }

}
