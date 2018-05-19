package karthi.example.com.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import karthi.example.com.myproject.R;
import karthi.example.com.myproject.adapter.ListViewAdapter;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class ViewPagerClass extends Fragment {

    private static final String TAG = "ViewPagerClass";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ArrayList<String> value;
//    private ListViewAdapter.PassDataValue passDataValue;

    public static ViewPagerClass getInstances(ArrayList<String> value) {
        ViewPagerClass viewPagerClass = new ViewPagerClass();
        Bundle bundle = new Bundle();
        bundle.putSerializable("V", value);
        viewPagerClass.setArguments(bundle);
        return viewPagerClass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.viewlayer,container, false);
        getActivity().setTitle("View Pager");
        value = getArguments().getStringArrayList("V");
//        Log.e(TAG, "ViewPagerClassssssssssss: " + value );
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab().setText("ListView"));
        tabLayout.addTab(tabLayout.newTab().setText("Array"));
        tabLayout.addTab(tabLayout.newTab().setText("Sqlight"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) view.findViewById(R.id.view);
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(0);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                viewPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }



    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {


            Fragment fragment = null;
            if (position == 0){
                fragment = new ListviewPage();
            }
            if (position == 1){
                fragment = ArrayPage.getInstances(value);
            }
            if (position == 2){
                fragment = new SqlightPage();
            }
            return fragment;
        }

        @Override
        public int getItemPosition(Object object1) {
//            if (object1 instanceof ArrayPage){
//////                ((ArrayPage) object1).setValues111(value);
//            }
            return POSITION_NONE;
        }


        @Override
        public int getCount() {
            return 3;
        }

    }
}
