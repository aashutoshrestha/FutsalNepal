package app.blackspring.com.futsalnepal.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.presentation.details.DetailsView;
import app.blackspring.com.futsalnepal.presentation.adapter.FutsalListAdapter;

public class ListFragment extends Fragment {

    private RecyclerView futsalList;
    private FutsalListAdapter futsalListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        futsalList = view.findViewById(R.id.rv_futsal_list);
        futsalList.setLayoutManager(new LinearLayoutManager(getContext()));
        futsalList.setHasFixedSize(true);

        futsalListAdapter = new FutsalListAdapter(position -> {
            startActivity(new Intent(getActivity(), DetailsView.class));
        });

        futsalListAdapter.getSubject().subscribe(position-> {
                startActivity(new Intent(getActivity(), DetailsView.class));
        });
        futsalList.setAdapter(futsalListAdapter);
    }
}
