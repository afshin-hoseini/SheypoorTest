package afshin.ir.sheypoortest.Activities.AdvertisementList;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import afshin.ir.sheypoortest.Entities.Advertisement;

/**
 * Created by afshinhoseini on 4/22/16.
 */
public class TestAdvertisementDataProvider {

    public static ArrayList<Advertisement> getTestAdvertisements(Context ctx) {


        ArrayList<Advertisement> advertisements = new ArrayList<>();

        try {

            String str_jsData = readTestData(ctx);
            JSONArray jsArr_advertisements = new JSONArray(str_jsData);

            for(int idx=0; idx<jsArr_advertisements.length(); idx++) {

                JSONObject js_advertisement = jsArr_advertisements.getJSONObject(idx);

                Advertisement advertisement = new Advertisement();
                advertisement.label = js_advertisement.optString("label");
                advertisement.address = js_advertisement.optString("address");
                advertisement.price = js_advertisement.optString("price");
                advertisement.date = new Date(System.currentTimeMillis() - js_advertisement.optLong("interval"));
                advertisement.imageUrl = js_advertisement.optString("imgurl");

                advertisements.add(advertisement);

            }


        }catch (Exception e) {

            e.printStackTrace();
        }

        return advertisements;


    }


    private static String readTestData(Context ctx) {

        String data = "";
        int readBytes = 0;
        byte buffer[] = new byte[512];


        try {

            InputStream is = ctx.getAssets().open("testData.json");


            while ((readBytes = is.read(buffer)) > -1) {

                data += new String(buffer, 0, readBytes);
            }

        }catch (Exception e) {

            e.printStackTrace();
        }

        return data;

    }

}
