package karthi.example.com.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.activity.CustomAdaptorActivity;
import karthi.example.com.myproject.data.CustomData;

/**
 * Created by AshokKumar on 19/10/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private static final String TAG = "CustomAdapter";
    private Context context;
    private List<CustomData> customDatas;

    public CustomAdapter(Context context,List<CustomData> customDatas) {
        this.context = context;
        this.customDatas = customDatas;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customrecyclelayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CustomData customData = customDatas.get(position);
        Picasso.with(context).load(customData.getImage()).into(holder.imageView);
        holder.title.setText(customData.getTitle());
        holder.rating.setText("Rating: "+ String.valueOf(customData.getRating()));
        holder.year.setText(String.valueOf(customData.getYear()));

//        String Gener = "";
//        for (String gner : customData.getGenre()){
//            Gener += gner + ",";
//        }
//
//        Gener = Gener.length() > 0 ?  Gener.substring( 0, Gener.length() -2) : Gener;
//        holder.gener.setText(Gener);
    }

    @Override
    public int getItemCount() {
        return customDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title,rating,year,gener;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            year = (TextView) itemView.findViewById(R.id.releaseYear);
            imageView = (ImageView) itemView.findViewById(R.id.image);
//            gener = (TextView) itemView.findViewById(R.id.genre);
        }

        @Override
        public void onClick(View view) {

            ;

            CustomData value = customDatas.get(getAdapterPosition());
//            Log.e(TAG, "onClickkkkkkkkkkkkkkkk: " + value.getGenre() );
            Intent intent = new Intent(context, CustomAdaptorActivity.class);
            intent.putExtra("IMAGE", value.getImage());
            intent.putExtra("TITLE",value.getTitle());
            intent.putExtra("RATING", String.valueOf(value.getRating()));
            intent.putExtra("YEAR", String.valueOf(value.getYear()));
            intent.putExtra("GENER", String.valueOf(value.getGenre()));
            context.startActivity(intent);
        }
    }
}
