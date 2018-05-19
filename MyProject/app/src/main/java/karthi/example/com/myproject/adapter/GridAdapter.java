package karthi.example.com.myproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.data.CustomData;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private Context context;
    private List<CustomData> list = new ArrayList<>();

    public GridAdapter(Context context, List<CustomData> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridviewlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(list.get(position).getImage()).into(holder.imageView);
        holder.title.setText(list.get(position).getTitle());

        String Genre = "";
        for ( String genre : list.get(position).getGenre()){
            Genre += genre + ",";
        }

        Genre = Genre.length() > 0 ? Genre.substring(0, Genre.length() - 2):Genre;
        holder.gender.setText(Genre);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,gender;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.titlegrid);
            gender = (TextView) itemView.findViewById(R.id.gendergrid);
            imageView = (ImageView) itemView.findViewById(R.id.imagegrid);
        }
    }
}
