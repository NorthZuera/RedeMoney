package br.com.testes.testederecycleview.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.testes.testederecycleview.MainActivity;
import br.com.testes.testederecycleview.R;

public class DiscussionFragment extends CarFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mList = savedInstanceState.getParcelableArrayList("mList");
        } else {
            mList = ((MainActivity) getActivity()).getCarsByCategory(3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discussion_fragment, container, false);

        return view;
    }
}