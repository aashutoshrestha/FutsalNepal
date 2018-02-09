package app.blackspring.com.futsalnepal.presentation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.presentation.adapter.BookingListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {


    private RecyclerView recyclerView;
    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_bookings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BookingListAdapter bookingListAdapter = new BookingListAdapter();
        recyclerView.setAdapter(bookingListAdapter);
    }
}
