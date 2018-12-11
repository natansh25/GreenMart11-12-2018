package infinity1087.android.com.examplehr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import infinity1087.android.com.examplehr.adapter.RecyclerAdapter;
import infinity1087.android.com.examplehr.adapter.RecyclerVeg;
import infinity1087.android.com.examplehr.model.ResponseDatum;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerVeg mAdapter;
    private List<ResponseDatum> mData;



    //TODO makes each and every variable name proper as the code base is growing its becoming difficult to manage without proper names creates confusion
    //TODO you just imrpove main and detail layout dont worry about other fruits and all not fetching will do in eveing its simple work on the layout and variables names refactoring properly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layout);
        Intent i = getIntent();
        mData = (List<ResponseDatum>) i.getSerializableExtra("yyy");
        Log.d("ttt", String.valueOf(mData));


        mRecyclerView = findViewById(R.id.recycler_view_detail);
        setUpRecyclerView(mData);
    }

    private void setUpRecyclerView(List<ResponseDatum> datumList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter=new RecyclerVeg(datumList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
