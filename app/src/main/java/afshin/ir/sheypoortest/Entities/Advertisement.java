package afshin.ir.sheypoortest.Entities;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

import afshin.ir.sheypoortest.R;

/**
 * Created by afshinhoseini on 4/22/16.
 */
public class Advertisement {

    public String imageUrl = "";
    public String label = "";
    public String address = "";
    public String price = "";
    public Date date = null;

// ____________________________________________________________________

    public String getTimeInfo(Context ctx) {

        long time = System.currentTimeMillis() - date.getTime();

        long seconds = time / 1000;
        String unit = ctx.getString(R.string.second);
        long timeValue = seconds;

        if(seconds >= 86400) {

            timeValue = seconds / 86400;
            unit = ctx.getString(R.string.day);
        }
        else if(seconds >= 3600) {

            timeValue = seconds / 3600;
            unit = ctx.getString(R.string.hour);
        }
        else if(seconds >= 60) {

            timeValue = seconds / 60;
            unit = ctx.getString(R.string.minute);
        }


        return timeValue + " " + unit + " " + ctx.getString(R.string.ago);


    }
// ____________________________________________________________________

}
