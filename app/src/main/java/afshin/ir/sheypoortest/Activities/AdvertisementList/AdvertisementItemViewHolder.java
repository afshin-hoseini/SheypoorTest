package afshin.ir.sheypoortest.Activities.AdvertisementList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import afshin.ir.sheypoortest.Entities.Advertisement;
import afshin.ir.sheypoortest.R;
import afshin.ir.sheypoortest.Utilities.StylingUtil;

/**
 * Created by afshinhoseini on 4/22/16.
 */
public class AdvertisementItemViewHolder extends RecyclerView.ViewHolder {

    Context ctx = null;
    ImageView img_advertisementPic = null;
    TextView txtLabel = null, txtAddress = null, txtTime = null, txtPrice = null;
    Advertisement advertisement = null;


// ____________________________________________________________________

    private AdvertisementItemViewHolder(View itemView) {

        super(itemView);

        this.ctx = itemView.getContext();
        this.img_advertisementPic = (ImageView) itemView.findViewById(R.id.img_advPic);
        this.txtLabel = (TextView) itemView.findViewById(R.id.txtLabel);
        this.txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
        this.txtTime = (TextView) itemView.findViewById(R.id.txtTime);
        this.txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);

        txtLabel.setTypeface(StylingUtil.getFont(StylingUtil.regularFont, ctx.getAssets()));
        txtAddress.setTypeface(StylingUtil.getFont(StylingUtil.regularFont, ctx.getAssets()));
        txtTime.setTypeface(StylingUtil.getFont(StylingUtil.regularFont, ctx.getAssets()));
        txtPrice.setTypeface(StylingUtil.getFont(StylingUtil.regularFont, ctx.getAssets()));

//        Drawable location = new ScaleDrawable(txtAddress.getCompoundDrawables()[0],0,0.1f,0.1f).getDrawable();
//        txtAddress.setCompoundDrawables(null, null, location, null);


        if(Build.VERSION.SDK_INT < 20) {
            Drawable location = ContextCompat.getDrawable(ctx, R.mipmap.ic_location_on_white_24dp);
            location.setColorFilter(ctx.getResources().getColor(R.color.advItemGrayIconOverlay), PorterDuff.Mode.MULTIPLY);
            txtAddress.setCompoundDrawables(null, null, location, null);


            Drawable time = ContextCompat.getDrawable(ctx, R.mipmap.ic_time_white_24dp);
            time.setColorFilter(ctx.getResources().getColor(R.color.advItemGrayIconOverlay), PorterDuff.Mode.MULTIPLY);
            txtTime.setCompoundDrawables(null, null, time, null);

        }
        else {

            txtAddress.getCompoundDrawables()[0].setColorFilter(ctx.getResources().getColor(R.color.advItemGrayIconOverlay), PorterDuff.Mode.MULTIPLY);
            txtTime.getCompoundDrawables()[0].setColorFilter(ctx.getResources().getColor(R.color.advItemGrayIconOverlay), PorterDuff.Mode.MULTIPLY);
        }

    }

// ____________________________________________________________________

    public void setAdvertisement(Advertisement advertisement, Bitmap image) {

        this.advertisement = advertisement;
        txtLabel.setText(advertisement.label);
        txtAddress.setText(advertisement.address);
        txtTime.setText(advertisement.getTimeInfo(ctx));
        txtPrice.setText(advertisement.price);

        if(image == null)
            img_advertisementPic.setImageResource(R.mipmap.no_image);
        else
            img_advertisementPic.setImageBitmap(image);
    }


// ____________________________________________________________________

    public static AdvertisementItemViewHolder getInstance(Context ctx, ViewGroup parent, boolean isStaggered) {

        int layoutResourceId = isStaggered ? R.layout.row_advertisement_staggered : R.layout.row_advertisement_normal;
        View itemView = LayoutInflater.from(ctx).inflate(layoutResourceId, parent, false);
        AdvertisementItemViewHolder viewHolder = new AdvertisementItemViewHolder(itemView);

        return viewHolder;
    }


// ____________________________________________________________________
}
