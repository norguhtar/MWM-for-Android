package org.metawatch.manager;

import org.metawatch.manager.MetaWatchService.Preferences;

import android.content.Context;
import android.graphics.Typeface;

public class FontCache {

    private FontCache(Context context) {
	Small = new FontInfo(context, "metawatch_8pt_5pxl_CAPS.ttf", 8, 5);
	Medium = new FontInfo(context, "metawatch_8pt_7pxl_CAPS.ttf", 8, 7);
	Large = new FontInfo(context, "metawatch_16pt_11pxl.ttf", 16, 11);
	SmallNumerals = new FontInfo(context, "metawatch_8pt_5pxl_Numerals.ttf", 8, 5);
	Numerals = new FontInfo(context, "metawatch_8pt_6pxl_Numerals.ttf", 8, 6);
    }

    private static FontCache instance = null;

    public static FontCache instance(Context context) {
	if (instance == null)
	    instance = new FontCache(context);
	return instance;
    }

    public class FontInfo {

	public FontInfo(Context context, String assetPath, int size, int realSize) {
	    face = Typeface.createFromAsset(context.getAssets(), assetPath);
	    this.size = size;
	    this.realSize = realSize;
	}

	public Typeface face;
	public int size;
	public int realSize;
    }

    public enum FontSize {
	AUTO, SMALL, MEDIUM, LARGE
    }

    public FontInfo Small = null;
    public FontInfo Medium = null;
    public FontInfo Large = null;
    public FontInfo SmallNumerals = null;
    public FontInfo Numerals = null;

    public FontInfo Get(FontSize size) {
	switch (size) {
	case AUTO:
	    return Get();

	case SMALL:
	    return Small;

	case MEDIUM:
	    return Medium;

	case LARGE:
	    return Large;
	}

	return null;
    }

    public FontInfo Get(int size) {
	switch (size) {
	case 1:
	    return Small;

	case 2:
	    return Medium;

	case 3:
	    return Large;
	}

	return null;
    }

    public FontInfo Get() {
	return Get(Preferences.fontSize);
    }

}
