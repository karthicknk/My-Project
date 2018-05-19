package karthi.example.com.myproject.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.adapter.ArrayAdapterClass;
import karthi.example.com.myproject.adapter.ListViewAdapter;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class ArrayPage extends Fragment {

    private static final String TAG = "ArrayPage";
    private ListView listView;
    private ArrayAdapterClass arrayAdapter;
    private ArrayList<String> strings = new ArrayList<>();
    private ArrayList<String> value1 = new ArrayList<>();

    public static ArrayPage getInstances(ArrayList<String> value) {
        ArrayPage arrayPage = new ArrayPage();
        Bundle bundle = new Bundle();
        bundle.putSerializable("V", value);
        arrayPage.setArguments(bundle);
        return arrayPage;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
////        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("Test"));
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
////        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
//    }

//    public void onMessageEvent(MessageEvent event) {
//        value1.add(event.getMessage());
//        callListview(value1);
//    }

//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String value = intent.getStringExtra("name");
//            value1.add(value);
//            callListview(value1);
//        }
//    };



//    public void setValues111(ArrayList<String> strings){
//        this.strings.clear();
//        this.strings.addAll(strings);
//        callListview(strings);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.listvewlayout, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        ArrayList<String> value = getArguments().getStringArrayList("V");
        callListview(value);

        return view;
    }

    public void callListview(ArrayList<String> list){
        arrayAdapter = new ArrayAdapterClass(getContext(),list);
        listView.setAdapter(arrayAdapter);
    }
}
