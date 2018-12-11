package infinity1087.android.com.examplehr.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import infinity1087.android.com.examplehr.R;
import infinity1087.android.com.examplehr.model.ResponseDatum;

public class RecyclerVeg extends RecyclerView.Adapter<RecyclerVeg.MyViewHolder> {

    List<ResponseDatum> mData;

    public RecyclerVeg(List<ResponseDatum> data) {
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_row_item, viewGroup, false);


        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ResponseDatum datum = mData.get(i);
        myViewHolder.txt_name.setText(datum.getP().getProductName());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder

    {
        TextView txt_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_item_name);
        }
    }
}
