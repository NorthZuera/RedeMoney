package br.com.testes.testederecycleview;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import org.w3c.dom.Text;

import br.com.testes.testederecycleview.domain.Car;


public class CarActivity extends AppCompatActivity {
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private Car car;
    private Drawer navigationDrawerLeft;
    private ViewGroup mRoot;
    private TextView tvDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TRANSITIONS
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                /* Explode trans1 = new Explode();
                trans1.setDuration(3000);
                Fade trans2 = new Fade();
                trans2.setDuration(3000);

                getWindow().setEnterTransition(trans1);
                getWindow().setReturnTransition(trans2); */

                TransitionInflater inflater = TransitionInflater.from(this);
                Transition transition = inflater.inflateTransition(R.transition.transitions);

                getWindow().setSharedElementEnterTransition(transition);

                Transition transition1 =  getWindow().getSharedElementEnterTransition();
                transition1.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        TransitionManager.beginDelayedTransition(mRoot, new Slide());
                        tvDescription.setVisibility( View.VISIBLE );
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });
            }
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);
        setContentView(R.layout.activity_car);

        if(savedInstanceState != null){
            car = savedInstanceState.getParcelable("car");
        }
        else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("car") != null) {
                car = getIntent().getExtras().getParcelable("car");
            } else {
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle( car.getModel() );

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(car.getModel());
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        mRoot = (ViewGroup) findViewById(R.id.ll_tv_description);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        ImageView ivCar = (ImageView) findViewById(R.id.iv_car);
        TextView tvModel = (TextView) findViewById(R.id.tv_model);
        TextView tvBrand = (TextView) findViewById(R.id.tv_brand);
        TextView tvDescription = (TextView) findViewById(R.id.tv_description);

        ivCar.setImageResource(car.getPhoto());
        tvModel.setText(car.getModel());
        tvBrand.setText(car.getBrand());
        tvDescription.setText( car.getDescription() );

        /* navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .withCloseOnClick(true)
                .withActionBarDrawerToggleAnimated(false)
                    .withActionBarDrawerToggle(new ActionBarDrawerToggle(this, new DrawerLayout(this), R.string.material_drawer_open, R.string.material_drawer_close) {
                        @Override
                        public void onDrawerSlide(View drawerView, float slideOffset) {
                            super.onDrawerSlide(drawerView, slideOffset);
                            navigationDrawerLeft.closeDrawer();
                            finish();
                        }
                })
                .build(); */

        //FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(CarActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_car_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) item.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView( item );
        }

        searchView.setSearchableInfo( searchManager.getSearchableInfo( getComponentName() ) );
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }

        return true;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("car", car);
    }

  /*  @Override
    public void onBackPressed() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(mRoot, new Slide());
            tvDescription.setVisibility( View.INVISIBLE );
        }

        super.onBackPressed();
    } */
}
