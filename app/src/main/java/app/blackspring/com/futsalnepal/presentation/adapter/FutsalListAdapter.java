package app.blackspring.com.futsalnepal.presentation.adapter;

import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.model.futsal.FutsalData;
import app.blackspring.com.futsalnepal.model.futsal.FutsalListItem;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/2/18.
 */

public class FutsalListAdapter extends RecyclerView.Adapter<FutsalListAdapter.ViewHolder> {

    private FutsalClick futsalClick;
    private PublishSubject<Integer> subject = PublishSubject.create();
    private List<FutsalListItem> futsalListItem;


    public FutsalListAdapter(FutsalClick futsalClick, List<FutsalListItem> futsalListItem) {
        this.futsalClick = futsalClick;
        this.futsalListItem = futsalListItem;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_futsal_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FutsalListItem item = futsalListItem.get(position);

        holder.futsalName.setText(item.getName());
        holder.futsalRating.setText(String.valueOf(item.getRating()));
        holder.futsalDistance.setText(item.getDistance());
        holder.futsalAddress.setText(item.getAddress());
        String open = "Open: " + item.getOpeningTime() + " - " + "Close: " + item.getClosingTime();

        holder.futsalOpen.setText(open);
        holder.futsalRatingBar.setRating((float)(item.getRating()));
    }

    @Override
    public int getItemCount() {
        return futsalListItem != null ? futsalListItem.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView futsalName, futsalRating, futsalDistance, futsalAddress, futsalOpen;
        public AppCompatRatingBar futsalRatingBar;
        public ViewHolder(View itemView) {
            super(itemView);

            futsalName = itemView.findViewById(R.id.tv_futsal_name);
            futsalRating = itemView.findViewById(R.id.tv_futsal_rating_text);
            futsalDistance = itemView.findViewById(R.id.tv_futsal_distance);
            futsalAddress = itemView.findViewById(R.id.tv_futsal_address);
            futsalOpen = itemView.findViewById(R.id.tv_futsal_open);
            futsalRatingBar = itemView.findViewById(R.id.rb_futsal_rating);

            itemView.setOnClickListener(view -> subject.onNext(getAdapterPosition()));
        }
    }

    public PublishSubject<Integer> getSubject(){
        return subject;
    }


    public interface FutsalClick{
        void onClick(int position);
    }

    public void updateDataSet(List<FutsalListItem> futsalListItem){
        this.futsalListItem = futsalListItem;
        notifyDataSetChanged();
    }
}
