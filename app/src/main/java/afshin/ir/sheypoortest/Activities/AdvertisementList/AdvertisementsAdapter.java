package afshin.ir.sheypoortest.Activities.AdvertisementList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

import afshin.ir.sheypoortest.Entities.Advertisement;

/**
 * Created by afshinhoseini on 4/22/16.
 */
public class AdvertisementsAdapter extends RecyclerView.Adapter<AdvertisementItemViewHolder> {

    ArrayList<Advertisement> advertisements = new ArrayList<>();
    Context ctx = null;
    boolean isStaggered = false;


    public AdvertisementsAdapter(Context ctx) {

        this.ctx = ctx;
    }


    public void setIsStaggered(boolean isStaggered) {

        this.isStaggered = isStaggered;
    }


    public void setAdvertisements(ArrayList<Advertisement> advertisements) {

        this.advertisements = advertisements;
    }

    @Override
    public int getItemViewType(int position) {
        return isStaggered ? 1 : 2 ;
    }


    @Override
    public AdvertisementItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return AdvertisementItemViewHolder.getInstance(ctx, parent, this.isStaggered);
    }

    @Override
    public void onBindViewHolder(AdvertisementItemViewHolder holder, int position) {

        holder.setAdvertisement(advertisements.get(position), null);
    }

    @Override
    public int getItemCount() {

        return advertisements == null ? 0 : advertisements.size();
    }
}
