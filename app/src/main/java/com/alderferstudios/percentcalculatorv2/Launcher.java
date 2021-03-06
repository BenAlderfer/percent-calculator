/**
 * @author Ben Alderfer
 * Alderfer Studios
 * Percent Calculator
 * Updated: 2/2/15
 */

package com.alderferstudios.percentcalculatorv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Launcher extends Activity
{
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        editor = shared.edit();

        saveScreenSize();
        editor.putBoolean("didJustGoBack", false);                                                //used later to determine animations
        editor.apply();

        Intent firstActivity;
        if (shared.getBoolean("combinedBox", false))
            firstActivity = new Intent(this, CombinedActivity.class);

        else
            firstActivity = new Intent(this, CostActivity.class);

        startActivity(firstActivity);
    }

    /**
     * Gets the screen size and saves it for later
     */
    private void saveScreenSize()
    {
        String size;
        if (isTablet(this))
            size = "tablet";
        else
            size = "phone";

        editor.putString("screenSize", size);
    }

    /**
     * Checks if the device is a tablet
     * @param context the Context
     * @return true if a tablet
     */
    private static boolean isTablet(Context context)
    {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
