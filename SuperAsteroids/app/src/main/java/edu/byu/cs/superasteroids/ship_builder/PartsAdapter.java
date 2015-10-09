package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import edu.byu.cs.superasteroids.content.ContentManager;

/**
 * The list adapter used to adapt a list of image IDs to a list of views containing the images of the
 * image IDs.
 */
public class PartsAdapter extends ArrayAdapter<Integer> {

	private List<Integer> parts;
	private Context context;
	
	public PartsAdapter(Context context, int resource, List<Integer> parts) {
		super(context, resource, parts);
		this.context = context;
		this.parts = parts;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	    ImageView view = new ImageView(context);
        Bitmap image = ContentManager.getInstance().getImage(parts.get(position));
        view.setImageBitmap(image);
        //view.setScaleX(0.5f);
        //view.setScaleY(0.5f);
        view.setPadding(5, 5, 5, 5);
		return view;
	}	
	
	public void setImages(List<Integer> parts) {
		this.parts.clear();
		for(int i : parts) {
			this.parts.add(i);
		}
		this.notifyDataSetChanged();
	}
}
