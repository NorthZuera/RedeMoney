package br.com.testes.testederecycleview.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.style.ImageSpan;

import br.com.testes.testederecycleview.R;
import br.com.testes.testederecycleview.fragments.CarFragment;
import br.com.testes.testederecycleview.fragments.DiscussionFragment;
import br.com.testes.testederecycleview.fragments.LuxuryCarFragment;

import static android.R.attr.fragment;

public class TabsAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles = {"DICAS", "INSPIRAR", "DISCUTIR"};

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);

        mContext = c;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;

        if(position == 0){ // FRAG PRINCIPAL
            frag = new CarFragment();
        }
        else if(position == 1){ // LUXURY CAR
            frag = new LuxuryCarFragment();
        }
        else if(position == 2){ // DISCUSSION FRAGMENT
            frag = new DiscussionFragment();
        }

        Bundle b = new Bundle();
        b.putInt("position", position);

        frag.setArguments(b);

        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return ( titles[position] );
    }
}
