package afshin.ir.sheypoortest.Utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StylingUtil {
	
	public static Typeface tf_regularFont = null;
	public static Typeface tf_boldFont = null;
	public static final String[] font_names = {"irsans.ttf","irsansbold.ttf"};
	public static final String fonts_folder = "Fonts/";
	public static final int regularFont = 0;
	public static final int boldFont = 1;
	
	
	
// ____________________________________________________________________
	/**
	 * Returns the specified {@link Typeface} and initialize it if needed.
	 * @param font Indicates the returning font.
	 * @param assetMan An assetManager. See {@link Context} getAssets() method.
	 * @return Appropriate {@link Typeface}
	 */
	public static Typeface getFont(int font, AssetManager assetMan)
	{
		switch(font)
		{
			case regularFont:
				
				if(tf_regularFont == null)
				{
					tf_regularFont = Typeface.createFromAsset(assetMan, fonts_folder + font_names[font]);
				}
				return tf_regularFont;

			case boldFont:

				if(tf_boldFont == null)
				{
					tf_boldFont = Typeface.createFromAsset(assetMan, fonts_folder + font_names[font]);
				}
				return tf_boldFont;
	
		}
		
		return null;
	}
// ____________________________________________________________________
	/**
	 * Sets an specific font to text view instances of a viewGroup. This
	 * function dectes for TextViews recursively.
	 * @param viewGroup The view group to set font to whole it's textViews.
	 * @param font The specific font to set.
	 */
	public static void setFont(ViewGroup viewGroup, Typeface font)
	{
		int children = viewGroup.getChildCount();
		int style =0;
		Typeface mainTypeFace = null;
		View child = null;
		
		for(int i=0; i < children; i++)
		{
			child = viewGroup.getChildAt(i);
			
			if(child instanceof TextView)
			{
				mainTypeFace = ((TextView) child).getTypeface();
				if(mainTypeFace != null)
				{
					style = mainTypeFace.getStyle();
				}
				else
				{
					style = Typeface.NORMAL;
				}
				
				((TextView) child).setTypeface(font,style);
			}
			else if(child instanceof ViewGroup)
			{
				setFont((ViewGroup)child, font);
			}
		}
	}
// ____________________________________________________________________	

}
