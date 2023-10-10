package com.example.login.beranda;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.login.R;
import com.example.login.databinding.ActivityNaviBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class NaviActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNaviBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNaviBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavi.toolbar);
        binding.appBarNavi.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navi);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navi);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

//    private void retrieveData() throws JSONException {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL.URL_JADWAL, null, new Response.Listener<JSONObject>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONObject js = response.getJSONObject("data");
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("jadwal", js.getString("jadwal"));
//                    lisdData.add(map);
//                    Ahome adData = new Ahome(Beranda.this, lisdData);
//                    rvData.setAdapter(adData);
//                    adData.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(jsonObjectRequest);
//    }

//kode oertama

//private void retrieveData() {
//    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL.URL_JADWAL, null, new Response.Listener<JSONObject>() {
//        @SuppressLint("NotifyDataSetChanged")
//        @Override
//        public void onResponse(JSONObject response) {
//            try {
//                JSONObject data = response.getJSONObject("data");
//                String jadwal = data.getString("jadwal");
//                HashMap<String, String> map = new HashMap<>();
//                map.put("jadwal", jadwal);
//                lisdData.add(map);
//                Ahome adData = new Ahome(Beranda.this, lisdData);
//                rvData.setAdapter(adData);
//                adData.notifyDataSetChanged();
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            // Handle error response here
//        }
//    });
//
//    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//    requestQueue.add(jsonObjectRequest);
//}

//    private void retrieveData() {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL.URL_JADWAL, null, new Response.Listener<JSONObject>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
////
////                    JSONObject js = response.getJSONObject("data");
//                    JSONArray jsonArray = response.getJSONArray("data");
//                    for (int i = 0; i < jsonArray.length(); i++){
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        String jadwal = jsonObject.getString("data");
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put("data",jadwal);
//                        lisdData.add(map);
//                    }
//                    Ahome ahome = new Ahome(Beranda.this,lisdData);
//                    rvData.setAdapter(ahome);
//                    ahome.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // Handle error response here
//            }
//        });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(jsonObjectRequest);
//    }