package com.loop.babushka.screen.home;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.loop.babushka.R;
import com.loop.babushka.screen.history.HistoryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    ActionBarDrawerToggle mDrawerToggle;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    TextView mUserName;
    CircleImageView mUserPhoto;

    private FragmentManager mFragmentManager;
    private Fragment mFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        View headerView = mNavigationView.getHeaderView(0);
        mUserName = headerView.findViewById(R.id.user_name);
        mUserPhoto = headerView.findViewById(R.id.user_photo);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    //  mFragment = new TimetableFragment();
                } else if (id == R.id.nav_history) {
                    //  mFragment = new HistoryFragment();
                } else if (id == R.id.nav_exit) {
                    logOut();
                }

                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.content_frame, mFragment);
                transaction.commit();

                if (mDrawer != null) {
                    mDrawer.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
    }

    private void logOut() {


    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
