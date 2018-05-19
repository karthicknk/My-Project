package karthi.example.com.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import karthi.example.com.myproject.adapter.ListViewAdapter;
import karthi.example.com.myproject.data.User;
import karthi.example.com.myproject.fragment.ArrayPage;
import karthi.example.com.myproject.fragment.BroadCastEventBus;
import karthi.example.com.myproject.fragment.CursorLoaderPage;
import karthi.example.com.myproject.fragment.CustomRecyclerView;
import karthi.example.com.myproject.fragment.GridViewPage;
import karthi.example.com.myproject.fragment.ViewPagerClass;
import karthi.example.com.myproject.helper.DataBaseHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ListViewAdapter.PassDataValue {

    private static final String TAG = "MainActivity";
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private SharedPreferences sharedPreferences;
    private ImageView imageView;
    private DataBaseHelper dataBaseHelper;
    private ArrayList<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataBaseHelper = new DataBaseHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences("User1", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Name = sharedPreferences.getString("MyName", "");
        String Email = sharedPreferences.getString("MyEmail", "");
        String Image = sharedPreferences.getString("Image", "");
        byte[] b = Base64.decode(Image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Default first page & icon select
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe, new CustomRecyclerView());
        fragmentTransaction.commit();
        navigationView.setCheckedItem(R.id.customRecyclerView);

        //image & name display in navigation drawer
        ImageView imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.myimageView11);
        imageView.setImageBitmap(bitmap);
        TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username);
        textView.setText(Name);
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

        int id = item.getItemId();

        if (id == R.id.customRecyclerView) {
            fragment = new CustomRecyclerView();

        } else if (id == R.id.gridView) {
            fragment = new GridViewPage();

        } else if (id == R.id.viewPager) {
            fragment = ViewPagerClass.getInstances(strings);

        } else if (id == R.id.cursorloader) {
            fragment = new CursorLoaderPage();

        }else if (id == R.id.localbroadandeventbus) {
            fragment = new BroadCastEventBus();

        }

        if (fragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainframe, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void Datasent(String value) {
//        Log.e(TAG, "MainActivityyyyyyyyyyyyyyyyyy: " + value );
        strings.add(value);

    }
}
