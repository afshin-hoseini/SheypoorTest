package afshin.ir.sheypoortest.Activities.AdvertisementList;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import afshin.ir.sheypoortest.Entities.Advertisement;
import afshin.ir.sheypoortest.R;
import afshin.ir.sheypoortest.Utilities.StylingUtil;

public class AdvertisementListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Handler handler = new Handler();
    DrawerLayout drawer = null;
    RecyclerView rv_advertisements = null;
    AdvertisementsAdapter advertisementsAdapter = null;
    ArrayList<Advertisement> advertisements = new ArrayList<>();
    boolean isStaggeredLayout = false;
    SwipeRefreshLayout swipeRefreshLayout = null;
    RecyclerView.LayoutManager layoutManager = null;

// ____________________________________________________________________

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_advertisement_list);
        ViewCompat.setLayoutDirection(getWindow().getDecorView(), ViewCompat.LAYOUT_DIRECTION_RTL);

        adjustTabs();

        //Setup actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbarTitle);

        //Setup drawer layout
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Setting up FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "I wish be your colleague :)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Setup navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).getIcon().setColorFilter(getResources().getColor(R.color.actionOrange), PorterDuff.Mode.MULTIPLY);

        //Setup advertisement recycler view
        rv_advertisements = (RecyclerView) findViewById(R.id.rv_advertisements);
        layoutManager = new LinearLayoutManager(this);
        rv_advertisements.setLayoutManager(layoutManager);
        advertisementsAdapter = new AdvertisementsAdapter(this);
        rv_advertisements.setAdapter(advertisementsAdapter);


        //Setup refresh layout
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(refreshListener);


        StylingUtil.setFont((ViewGroup) getWindow().getDecorView(), StylingUtil.getFont(StylingUtil.regularFont, getAssets()));
        //Changes the whole view's font after a little delay.
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                StylingUtil.setFont((ViewGroup) getWindow().getDecorView(), StylingUtil.getFont(StylingUtil.regularFont, getAssets()));
            }
        }, 500);


        setupTestData();

    }

// ____________________________________________________________________


    private void setupTestData() {

        this.advertisements = TestAdvertisementDataProvider.getTestAdvertisements(this);
        advertisementsAdapter.setAdvertisements(this.advertisements);
        advertisementsAdapter.notifyDataSetChanged();
    }

// ____________________________________________________________________

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

// ____________________________________________________________________

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.advertisements_list, menu);
        return true;
    }

// ____________________________________________________________________

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_gallery) {

            isStaggeredLayout = !isStaggeredLayout;

            int iconResource = isStaggeredLayout ? R.mipmap.ic_list_bulleted_white_24dp : R.mipmap.ic_gallery_white_24dp;
            item.setIcon(iconResource);


            rv_advertisements.setLayoutManager(isStaggeredLayout ? new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) : new LinearLayoutManager(this));
            advertisementsAdapter.setIsStaggered(isStaggeredLayout);
            advertisementsAdapter.notifyDataSetChanged();



            return true;
        }

        return super.onOptionsItemSelected(item);
    }

// ____________________________________________________________________

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Toast.makeText(this, "Not designed", Toast.LENGTH_SHORT).show();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


// ____________________________________________________________________

    /**
     * Adjusts fixed tab like views.
     */
    private void adjustTabs() {

        LinearLayout tabFilter = (LinearLayout) findViewById(R.id.tab_filter);
        ImageView icon = (ImageView) tabFilter.findViewById(R.id.img_icon);
        icon.setImageResource(R.mipmap.ic_filter);
        TextView lbl = (TextView) tabFilter.findViewById(R.id.txtLabel);
        lbl.setText(R.string.filters);

        LinearLayout tabGroups = (LinearLayout) findViewById(R.id.tab_groups);
        icon = (ImageView) tabGroups.findViewById(R.id.img_icon);
        icon.setImageResource(R.mipmap.ic_groups_white_24dp);
        lbl = (TextView) tabGroups.findViewById(R.id.txtLabel);
        lbl.setText(R.string.groups);

        LinearLayout tabState = (LinearLayout) findViewById(R.id.tab_state);
        icon = (ImageView) tabState.findViewById(R.id.img_icon);
        icon.setImageResource(R.drawable.ic_state_tab_icon);
        lbl = (TextView) tabState.findViewById(R.id.txtLabel);
        lbl.setText(R.string.state);

    }

// ____________________________________________________________________

    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 2000);

        }
    };

// ____________________________________________________________________
}
