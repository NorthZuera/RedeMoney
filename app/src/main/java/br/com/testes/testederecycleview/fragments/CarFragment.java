package br.com.testes.testederecycleview.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import br.com.testes.testederecycleview.CarActivity;
import br.com.testes.testederecycleview.MainActivity;
import br.com.testes.testederecycleview.R;
import br.com.testes.testederecycleview.adapters.CarAdapter;
import br.com.testes.testederecycleview.domain.Car;
import br.com.testes.testederecycleview.interfaces.RecyclerViewOnClickListenerHack;

public class CarFragment extends Fragment implements RecyclerViewOnClickListenerHack, View.OnClickListener {
    protected static final String TAG = "LOG";
    protected RecyclerView mRecyclerView;
    protected List<Car> mList;
    protected FloatingActionMenu fab;
    //protected android.support.design.widget.FloatingActionButton fab;
    protected SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //TRANSITIONS
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { }

        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mList = savedInstanceState.getParcelableArrayList("mList");
        } else {
            mList = ((MainActivity) getActivity()).getCarsByCategory(1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_car, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0) {
                    fab.hideMenuButton(true);
                }
                else {
                    fab.showMenuButton(true);
                }

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();

                CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();
            }
        });
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        CarAdapter adapter = new CarAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);
        setFloatingActionButton( view );

        return view;
    }


    public void setFloatingActionButton(View view) {
        fab = (FloatingActionMenu) getActivity().findViewById(R.id.menu1);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean b) {
                //Toast.makeText(getActivity(), "Is menu opened? " + (b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });
        fab.showMenuButton(true);
        fab.setClosedOnTouchOutside(true);

        FloatingActionButton fab1 = (FloatingActionButton) getActivity().findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) getActivity().findViewById(R.id.fab2);
        FloatingActionButton fab3 = (FloatingActionButton) getActivity().findViewById(R.id.fab3);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
    }

    @Override
    public void onClickListener(View view, int position) {

        Intent intent = new Intent(getActivity(), CarActivity.class);
        intent.putExtra("car", mList.get(position));

        //TRANSITIONS
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View ivCar = view.findViewById(R.id.iv_car);
            View tvModel = view.findViewById(R.id.tv_model);
            View tvBrand = view.findViewById(R.id.tv_brand);

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    Pair.create(ivCar, "element1"),
                    Pair.create(tvModel, "element2"),
                    Pair.create(tvBrand, "element3"));

            getActivity().startActivity( intent, options.toBundle() );
        }
        else {
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void onLongPressClickListener(View view, int position) {

    }


    static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh) {
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv,
                                rv.getChildPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onClickListener(cv,
                                rv.getChildPosition(cv));
                    }

                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {}

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
    }


    @Override
    public void onClick(View v) {
        Intent it = null;

        switch (v.getId()) {
            case R.id.fab1 : //Setando uma função quando clicar no FloatingActionButtom Facebook no SubMenu de fab(Floating Action Menu)
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("https://www.facebook.com/kinkapps/"));
                startActivity(it);
                break;

            case R.id.fab2 : //Setando uma função quando clicar no FloatingActionButtom Youtube no SubMenu de fab(Floating Action Menu)
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("https://www.instagram.com/kinkapps/"));
                startActivity(it);
                break;

            case R.id.fab3 : //Setando uma função quando clicar no FloatingActionButtom Youtube no SubMenu de fab(Floating Action Menu)
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("https://twitter.com/kink_apps"));
                startActivity(it);
                break;
        }
    }


        @Override
        public void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putParcelableArrayList("mList", (ArrayList<Car>) mList);
        }
}