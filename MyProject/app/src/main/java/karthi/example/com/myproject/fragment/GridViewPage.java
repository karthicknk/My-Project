package karthi.example.com.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.adapter.GridAdapter;
import karthi.example.com.myproject.data.CustomData;
import karthi.example.com.myproject.interfaces.Communication;
import karthi.example.com.myproject.volley.NetworkController;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class GridViewPage extends Fragment implements Communication{

    private RecyclerView recyclerView;
    private List<CustomData> list = new ArrayList<>();
    private GridAdapter gridAdapter;
    private String url = "https://api.androidhive.info/json/movies.json";
    private NetworkController networkController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.gridlayout,container,false);
        getActivity().setTitle("GridView");

        networkController = NetworkController.getInstance(getContext());
        networkController.JsonFunction(url,this);
        gridAdapter = new GridAdapter(getContext(),list);

        recyclerView = (RecyclerView) view.findViewById(R.id.gridview) ;
        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(gridAdapter);

        return view;
    }

    @Override
    public void Success(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject jsonObject = array.getJSONObject(i);
                CustomData customData = new CustomData();
                customData.setImage(jsonObject.getString("image"));
                customData.setTitle(jsonObject.getString("title"));

                ArrayList<String> strings = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("genre");
                for (int j = 0; j < jsonArray.length(); j++) {
                    String value =(String) jsonArray.get(j);
                    strings.add(value);
                }
                customData.setGenre(strings);

                list.add(customData);

            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                gridAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void Error(String error) {

    }
}
