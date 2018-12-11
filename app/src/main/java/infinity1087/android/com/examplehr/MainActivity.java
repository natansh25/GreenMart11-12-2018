package infinity1087.android.com.examplehr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import infinity1087.android.com.examplehr.Fragment.SimpleFragmentPageAdapter;
import infinity1087.android.com.examplehr.Service.ApiClient;
import infinity1087.android.com.examplehr.Service.ApiInterface;
import infinity1087.android.com.examplehr.adapter.RecyclerAdapter;
import infinity1087.android.com.examplehr.model.Example;
import infinity1087.android.com.examplehr.model.Pojo;
import infinity1087.android.com.examplehr.model.ResponseDatum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//https://api.themoviedb.org/3/movie/353081/videos?api_key=00bab64ed019eded1ab3d951af1bb2a0&language=en-US
// http://portfolio.barodaweb.com/Dynamic/egreenapi/api/ProductGroupId/Get

    //New
//    http://portfolio.barodaweb.com/Dynamic/egreenapi/api/Product/Get

//    image base url
//    http://image.barodaweb.net/api/EGreen/Magic/200/ProductGroup-Vegetables/a1.jpg/100
//    http://image.barodaweb.net/api/EGreen/Magic/<Width>/ProductGroup-<ProductGroupName>/<ProductImage>/<Quality>

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private ApiInterface apiInterface;
    private List<ResponseDatum> results;

    //--fake data variable---

    List<Pojo> mPojos=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v4.view.ViewPager viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPageAdapter adapter = new SimpleFragmentPageAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //------------------------ our code begins-------------------

        setupRecyclerView();
        callRetrofit();

        //TODO= read below  create a new android proejct and take navigation drawer template and then add this codes whats missing

        //--------------fake data creation --------------------

        // upload more images to firebase storage and get download url and add more items like below to display more items in recycler view

        Pojo pojo = new Pojo("https://firebasestorage.googleapis.com/v0/b/snehnatansh.appspot.com/o/img1.jpg?alt=media&token=922bc5fb-9571-4777-8a89-4efe1da5ecc9");
        mPojos.add(pojo);
        Pojo pojo1 = new Pojo("https://firebasestorage.googleapis.com/v0/b/snehnatansh.appspot.com/o/img1.jpg?alt=media&token=922bc5fb-9571-4777-8a89-4efe1da5ecc9");
        mPojos.add(pojo1);

        mAdapter = new RecyclerAdapter(mPojos);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupRecyclerView() {

        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    private void callRetrofit() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getcontacts();
        call.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                 results = response.body().getResponseData();
                /*ResponseDatum datum=results.get(0);
                datum.getPG().getProductGroupName();*/



/*
                String url = String.valueOf(response.raw().request().url());
                         Log.d("Hey", datum.getPG().getProductGroupName());*/
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error Loading", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }  else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void img_btn1(View view) {

        Intent i = new Intent(MainActivity.this,DetailLayout.class);
        i.putExtra("yyy", (Serializable) results);
        startActivity(i);
    }
}
