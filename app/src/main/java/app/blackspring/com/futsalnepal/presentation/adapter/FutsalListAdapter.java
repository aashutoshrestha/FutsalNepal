package app.blackspring.com.futsalnepal.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.blackspring.com.futsalnepal.R;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/2/18.
 */

public class FutsalListAdapter extends RecyclerView.Adapter<FutsalListAdapter.ViewHolder> {

    FutsalClick futsalClick;
    private PublishSubject<Integer> subject = PublishSubject.create();


    public FutsalListAdapter(FutsalClick futsalClick) {
        this.futsalClick = futsalClick;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_futsal_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

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


    public interface FutsalClick{
        void onClick(int position);
    }
}
