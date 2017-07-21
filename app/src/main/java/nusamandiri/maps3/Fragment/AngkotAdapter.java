package nusamandiri.maps3.Fragment;

/**
 * Created by Mr.R on 7/16/2017.
 */


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import nusamandiri.maps3.R;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class AngkotAdapter extends RecyclerView.Adapter<AngkotAdapter.ViewHolder> {
    private static final String TAG = "AngkotAdapter";

    private String[] mDataSet,mDataset2;
    private int[] mDataset3;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView, textView2;
        private final ImageView icon_item;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.judul);
            textView2 = (TextView) v.findViewById(R.id.isi);
            icon_item = (ImageView) v.findViewById(R.id.icon_item);
        }

        public TextView getTextView() {
            return textView;
        }

        public TextView getTextView2() {
            return textView2;
        }

        public ImageView getImageView() {
            return icon_item;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public AngkotAdapter(String[] dataSet, String[] dataset2, int[] dataset3) {
        this.mDataSet = dataSet;
        this.mDataset2 = dataset2;
        this.mDataset3 = dataset3;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataSet[position]);
        viewHolder.getTextView2().setText(mDataset2[position]);
        viewHolder.getImageView().setImageResource(mDataset3[position]);
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
