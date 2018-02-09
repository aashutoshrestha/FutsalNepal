package app.blackspring.com.futsalnepal.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.blackspring.com.futsalnepal.R;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/7/18.
 */

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.ViewHolder> {

    private PublishSubject<Integer> subject = PublishSubject.create();


    public BookingListAdapter() {
    }

    @Override
    public BookingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_booking, parent, false);
        return new BookingListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookingListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                subject.onNext(getAdapterPosition());
            });
        }
    }

    public PublishSubject<Integer> getSubject(){
        return subject;
    }

}
