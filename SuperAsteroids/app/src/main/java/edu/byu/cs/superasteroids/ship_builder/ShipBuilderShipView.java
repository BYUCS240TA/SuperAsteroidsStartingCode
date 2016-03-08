package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import edu.byu.cs.superasteroids.R;
import edu.byu.cs.superasteroids.game.IGameController;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.InputManager;

/**
 * A game view extended to draw a parallax space background
 */
public class ShipBuilderShipView extends View implements IGameController
{

    private Canvas currentCanvas;
	private Paint paint;
	private IGameController studentDelegate;
    private boolean shouldDraw = true;
	
	private Bitmap fg;
	
	private final float fgSpeed = 0.0025f;
	
	private float fgImage1y;
	private float fgImage2y;
    private Timer timer;
	
	private Rect fgSrc;

	
	public ShipBuilderShipView(Context context, IGameController studentDelegate) {
		super(context);
		paint = new Paint();
		this.studentDelegate = studentDelegate;

	}

    public void runLoop() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if(shouldDraw)
                    ShipBuilderShipView.this.postInvalidate();
            }

        }, 0, 1000 / 30);
    }

    public void stopDrawing() {
        shouldDraw = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DrawingHelper.INSTANCE.setCanvas(canvas);
        DrawingHelper.INSTANCE.setViewHeight(getMeasuredHeight());
        DrawingHelper.INSTANCE.setViewWidth(getMeasuredWidth());
        currentCanvas = canvas;
        draw(DrawingHelper.INSTANCE);
        update(InputManager.INSTANCE);
    }

	@Override
	public void draw(DrawingHelper drawingHelper) {
		
		int width = this.getMeasuredWidth();//currentCanvas.getWidth();
		int height = this.getMeasuredHeight();//currentCanvas.getHeight();

		Rect dest = new Rect(0,0,width,0);	
		dest.top = (int)(fgImage1y * height);
		dest.bottom = dest.top + height;
        currentCanvas.drawBitmap(fg, fgSrc, dest, paint);
		
		dest.top = (int)(fgImage2y * height);
		dest.bottom = dest.top + height;
        currentCanvas.drawBitmap(fg, fgSrc, dest, paint);

        if(studentDelegate != null)
            studentDelegate.draw(DrawingHelper.INSTANCE);
	}

    @Override
    public void onMeasure(int w, int h) {

        setMeasuredDimension(MeasureSpec.getSize(w), MeasureSpec.getSize(h));
    }

	@Override
	public void loadContent(ContentManager content) {

		fg = content.loadBitmap(R.drawable.starfield);

		fgImage1y = -1;
		fgImage2y = 0;

		fgSrc = new Rect(0, 0, fg.getWidth(), fg.getHeight());

        if(studentDelegate != null)
		    studentDelegate.loadContent(content);
	}

    @Override
    public void unloadContent(ContentManager content) {
        fg.recycle();
    }

	@Override
	public void update(InputManager inputManager) {

		fgImage1y += fgSpeed;
		fgImage2y += fgSpeed;
		
		if(fgImage1y >= 1)
			fgImage1y = -1;
		if(fgImage2y >= 1)
			fgImage2y = -1;

        if(studentDelegate != null)
            studentDelegate.update(InputManager.INSTANCE);

    }

}
