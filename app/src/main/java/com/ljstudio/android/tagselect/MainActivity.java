package com.ljstudio.android.tagselect;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ljstudio.android.tagselect.views.flowlayout.FlowLayout;
import com.ljstudio.android.tagselect.views.flowlayout.TagAdapter;
import com.ljstudio.android.tagselect.views.flowlayout.TagFlowLayout;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private String[] mValues = new String[]
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private TagFlowLayout tagFlowLayout;
    private Set<Integer> mSelectPosSet = new HashSet<>();
    private ValueTagAdapter valueTagAdapter;
    private LayoutInflater mInflater;
    private int mValue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInflater = LayoutInflater.from(MainActivity.this);

        tagFlowLayout = (TagFlowLayout) this.findViewById(R.id.id_tag_layout);

        valueTagAdapter = new ValueTagAdapter(mValues);
        tagFlowLayout.setAdapter(valueTagAdapter);
        valueTagAdapter.setSelectedList(0);

        tagFlowLayout.setMaxSelectPosition(6);

        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                System.out.println("position-->" + position);

                if (mValue == position) {

                } else {
                    mSelectPosSet.clear();
                    mValue = position;
                    mSelectPosSet.add(mValue);
                }

                valueTagAdapter.setSelectedList(mSelectPosSet);
                return true;
            }
        });

//        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
//            @Override
//            public void onSelected(Set<Integer> selectPosSet) {
//                mSelectPosSet = selectPosSet;
//            }
//        });
    }

    private class ValueTagAdapter extends TagAdapter {

        public ValueTagAdapter(String[] datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, Object o) {
            TextView tv = (TextView) mInflater.inflate(R.layout.layout_tag_text, tagFlowLayout, false);
            tv.setText(o.toString());
            System.out.println("ValueTagAdapter-->position-->" + position);

            if (position < 6) {
                tv.setTextColor(Color.parseColor("#5c5959"));
            } else {
                tv.setTextColor(Color.parseColor("#cc0000"));
            }
            return tv;
        }
    }
}
