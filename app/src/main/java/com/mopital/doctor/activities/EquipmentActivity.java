package com.mopital.doctor.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mopital.doctor.R;
import com.mopital.doctor.adapters.ExpandableListAdapter;
import com.mopital.doctor.core.ServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.models.Equipment;
import com.mopital.doctor.models.wrappers.EquipmentListWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AlperCem on 24.4.2015.
 */
public class EquipmentActivity extends ActionBarActivity {

    @InjectView(R.id.app_bar)
    Toolbar toolbar;

    @InjectView(R.id.equipment_LV)
    ExpandableListView equipmentList;

    List<String> listDataHeader;
    HashMap<String, Equipment> listDataChild;

    private ServerApi api;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        api = ServerApiProvider.serverApi();
        context = this;

        // preparing list data
        getAllEquipments();
    }

    public void getAllEquipments() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, Equipment>();
        api.getAllEquipments(this, new Response.Listener<EquipmentListWrapper>() {
                    @Override
                    public void onResponse(EquipmentListWrapper response) {
                        int count = 0;
                        List<Equipment> equipments = response.getEquipmentList();
                        for (Equipment equipment : equipments) {
                            listDataHeader.add(equipment.getName());
                            listDataChild.put(listDataHeader.get(count), equipment);
                            count++;
                        }
                        ExpandableListAdapter listAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);
                        equipmentList.setAdapter(listAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

}
