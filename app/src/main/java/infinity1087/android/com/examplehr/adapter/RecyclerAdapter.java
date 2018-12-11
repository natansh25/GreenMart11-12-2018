package infinity1087.android.com.examplehr.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import infinity1087.android.com.examplehr.R;
import infinity1087.android.com.examplehr.model.Pojo;
import infinity1087.android.com.examplehr.model.ResponseDatum;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyVIewHolder> {

    List<Pojo> mList;
    //List<ResponseDatum> datumList;

    public RecyclerAdapter(List<Pojo> list) {

        mList = list;

    }

    @NonNull
    @Override
    public MyVIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);


        return new MyVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVIewHolder myVIewHolder, int i) {

        Pojo pojo = mList.get(i);
       // ResponseDatum datum=datumList.get(i);
        Picasso.get().load(pojo.getPath()).error(R.drawable.ic_launcher_background).placeholder(R.drawable.img1).into(myVIewHolder.img);
        Log.d("nan",pojo.getPath());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyVIewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public MyVIewHolder(@NonNull View itemView) {

            super(itemView);

            img = itemView.findViewById(R.id.img_item_row);
        }
    }
}
