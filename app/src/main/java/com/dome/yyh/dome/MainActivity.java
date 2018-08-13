package com.dome.yyh.dome;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.dome.yyh.dome.VR.GyroscopeManager;
import com.dome.yyh.dome.VR.ImageVo;
import com.dome.yyh.dome.VR.MyItemTouchCallback;
import com.dome.yyh.dome.adapter.GyroscopeImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity{
    private static final String PIC1_URL =
            "http://wx2.sinaimg.cn/large/6e9ad2bdly1fnih8s6on4j21401401kz.jpg";
    private static final String PIC2_URL =
            "http://vrlab-public.ljcdn.com//release//vradmin//1000000020129136//images//FF41C450.png";
    private static final String PIC3_URL =
            "http://wx2.sinaimg.cn/large/6e9ad2bdly1fnih8uqgkuj2140140b2b.jpg";


    private List<ImageVo> mData = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mianactivity);
        initView();
        GyroscopeManager.getInstance().register(this);
    }
    private void initView() {


        recyclerView = findViewById(R.id.rv_image_list);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        //   recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
        final GyroscopeImageAdapter gyroscopeImageAdapter = new GyroscopeImageAdapter(this, mData);
        MyItemTouchCallback callback = new MyItemTouchCallback(gyroscopeImageAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        recyclerView.setAdapter(gyroscopeImageAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }


    private void initData() {
        mData.add(new ImageVo(PIC1_URL, "1"));
        mData.add(new ImageVo(PIC2_URL, "2"));
        mData.add(new ImageVo(PIC1_URL, "3"));
        mData.add(new ImageVo(PIC2_URL, "4"));
        mData.add(new ImageVo(PIC1_URL, "5"));
        mData.add(new ImageVo(PIC2_URL, "6"));
        mData.add(new ImageVo(PIC1_URL, "7"));
        mData.add(new ImageVo(PIC2_URL, "8"));
        mData.add(new ImageVo(PIC1_URL, "9"));
        mData.add(new ImageVo(PIC1_URL, "1"));
        mData.add(new ImageVo(PIC2_URL, "2"));
        mData.add(new ImageVo(PIC1_URL, "3"));
        mData.add(new ImageVo(PIC2_URL, "4"));
        mData.add(new ImageVo(PIC1_URL, "5"));
        mData.add(new ImageVo(PIC2_URL, "6"));
        mData.add(new ImageVo(PIC1_URL, "7"));
        mData.add(new ImageVo(PIC2_URL, "8"));
        mData.add(new ImageVo(PIC1_URL, "9"));
    }



}
