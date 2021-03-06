package com.mopital.doctor.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mopital.doctor.R;
import com.mopital.doctor.activities.EquipmentActivity;
import com.mopital.doctor.activities.MainActivity;
import com.mopital.doctor.activities.SignInActivity;
import com.mopital.doctor.activities.StatisticsActivity;
import com.mopital.doctor.adapters.DrawerAdapter;
import com.mopital.doctor.core.PreferenceService;
import com.mopital.doctor.models.DrawerItem;
import com.mopital.doctor.utils.CircularImageView;
import com.mopital.doctor.utils.Constants;
import com.mopital.doctor.view.controllers.EmergencyCallPopupController;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerFragment extends Fragment implements DrawerAdapter.ClickListener {

    private RecyclerView recyclerView;

    public static final String PREF_FILE_NAME = "navigation_pref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private DrawerAdapter mDrawerAdapter;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private View containerView;
    private TextView userNameTV;
    private ImageView userImage;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null)
            mFromSavedInstanceState = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        userNameTV = (TextView) layout.findViewById(R.id.userName);
        userImage = (CircularImageView) layout.findViewById(R.id.user_icon);

        mDrawerAdapter = new DrawerAdapter(getActivity(), getData());
        mDrawerAdapter.setClickListener(this);
        recyclerView.setAdapter(mDrawerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }
    //Navigation Drawer data
    public static List<DrawerItem> getData() {
        List<DrawerItem> data = new ArrayList<>();
        String[] titles = {"Patients", "Equipments", "Statistics", "Emergency Call", "About", "Logout"};
        for (int i = 0; i < titles.length; i++) {
            DrawerItem current;
            switch (i) {
                case 0:
                    current = new DrawerItem(titles[i], R.drawable.ic_patients);
                    data.add(current);
                    break;
                case 1:
                    current = new DrawerItem(titles[i], R.drawable.ic_equipments);
                    data.add(current);
                    break;
                case 2:
                    current = new DrawerItem(titles[i], R.drawable.ic_statistics);
                    data.add(current);
                    break;
                case 3:
                    current = new DrawerItem(titles[i], R.drawable.ic_call);
                    data.add(current);
                    break;
                case 4:
                    current = new DrawerItem(titles[i], R.drawable.ic_about);
                    data.add(current);
                    break;
                case 5:
                    current = new DrawerItem(titles[i], R.drawable.ic_logout);
                    data.add(current);
                    break;
                default:
                    break;
            }
        }
        return data;
    }

    public void setUserProfile(String name) {
        userNameTV.setText(name);
        setUserImage(name);
    }

    private void setUserImage(String user) {
        Bitmap bitmap;
        if(user.contains("lper"))
            bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_alper)).getBitmap();
        else if(user.contains("hmet"))
            bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_ahmet)).getBitmap();
        else
            bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_default_picture)).getBitmap();

        userImage.setImageBitmap(bitmap);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        containerView = getActivity().findViewById(fragmentId);

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.6)
                    toolbar.setAlpha(1 - slideOffset);
            }
        };

        //Open the drawer if it is the very first time
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);

        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), EquipmentActivity.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(), StatisticsActivity.class));
                break;
            case 3:
                EmergencyCallPopupController controller = new EmergencyCallPopupController(containerView.getContext());
                controller.showPopup();
                break;
            case 4:
                Toast.makeText(getActivity(), "This application is developed by Bilkent University Computer Science students.", Toast.LENGTH_LONG).show();
                break;
            case 5:
                PreferenceService.removeCredentials(containerView.getContext());
                Intent i = new Intent(containerView.getContext(), SignInActivity.class);
                startActivityForResult(i, Constants.LOG_OUT_REQUEST_CODE);
                break;
            default:
                break;
        }
    }
}