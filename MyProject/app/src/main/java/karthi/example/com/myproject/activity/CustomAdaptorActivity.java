package karthi.example.com.myproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import karthi.example.com.myproject.R;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class CustomAdaptorActivity extends AppCompatActivity {

    private static final String TAG = "CustomAdaptorActivity";
    private TextView title, genre, year, rating;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customadapteranotherlayout);
//        this.setTitle("aaaaaaaaaaaaaaa");

        //back button function
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViewes();
        initListener();
        initObjects();
    }

    //back button function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViewes() {
        imageView = (ImageView) findViewById(R.id.image);
        title = (TextView) findViewById(R.id.title);
        rating = (TextView) findViewById(R.id.rating);
        genre = (TextView) findViewById(R.id.genre);
        year = (TextView) findViewById(R.id.releaseYear);

        String Image = getIntent().getStringExtra("IMAGE");
        String Title = getIntent().getStringExtra("TITLE");
        String Rating = getIntent().getStringExtra("RATING");
        String Genre = getIntent().getStringExtra("GENER");
        String Year = getIntent().getStringExtra("YEAR");
//        Log.e(TAG, "onClickkkkkkkkkkkkkkkk: " + Genre );

        Picasso.with(this).load(Image).into(imageView);
        title.setText(Title);
        rating.setText("Rating: "+ Rating);
        genre.setText(Genre);
        year.setText("Year: " + Year);
    }

    private void initListener() {

    }

    private void initObjects() {

    }
}
