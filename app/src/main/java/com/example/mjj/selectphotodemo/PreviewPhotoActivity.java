package com.example.mjj.selectphotodemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mjj.selectphotodemo.utils.Bimp;
import com.example.mjj.selectphotodemo.zoom.PhotoView;
import com.example.mjj.selectphotodemo.zoom.ViewPagerFixed;

import java.util.ArrayList;

/**
 * Description：进行图片浏览,可删除
 * <p>
 * Created by Mjj on 2016/12/2.
 */

public class PreviewPhotoActivity extends Activity {

    private Intent intent;
    // 返回图标
    private TextView back_bt;
    // 发送按钮
    private Button send_bt;
    //删除图标
    private ImageView del_bt;

    //获取前一个activity传过来的position
    private int position;
    //当前的位置
    private int location = 0;

    private ArrayList<View> listViews = null;
    private ViewPagerFixed pager;
    private MyPageAdapter adapter;

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_gallery);// 切屏到主界面
        mContext = this;
        back_bt = (TextView) findViewById(R.id.gallery_back);
        send_bt = (Button) findViewById(R.id.send_button);
        del_bt = (ImageView) findViewById(R.id.gallery_del);
        // 返回键监听
        back_bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        send_bt.setOnClickListener(new GallerySendListener());
        del_bt.setOnClickListener(new DelListener());
        intent = getIntent();
        position = Integer.parseInt(intent.getStringExtra("position"));
        isShowOkBt();
        pager = (ViewPagerFixed) findViewById(R.id.gallery01);
        pager.addOnPageChangeListener(pageChangeListener);
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            Bitmap tempBit = Bimp.tempSelectBitmap.get(i).getBitmap();
            initListViews(tempBit);
        }

        adapter = new MyPageAdapter(listViews);
        pager.setAdapter(adapter);
        pager.setPageMargin(10);
        pager.setOffscreenPageLimit(9);

        int id = intent.getIntExtra("ID", 0);
        pager.setCurrentItem(id);
    }

    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

        public void onPageSelected(int arg0) {
            location = arg0;
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void initListViews(Bitmap bm) {
        if (listViews == null)
            listViews = new ArrayList<View>();
        PhotoView img = new PhotoView(this);
        img.setImageBitmap(bm);
        img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        listViews.add(img);
    }

    // 删除按钮添加的监听器
    private class DelListener implements OnClickListener {

        public void onClick(View v) {
            if (listViews.size() == 1) {
                Bimp.tempSelectBitmap.clear();
                Bimp.max = 0;
                back_bt.setText("" + Bimp.tempSelectBitmap.size() + " / 9");
                Intent intent = new Intent("data.broadcast.action");
                sendBroadcast(intent);
                finish();
            } else {
                Bimp.tempSelectBitmap.remove(location);
                Bimp.max--;
                pager.removeAllViews();
                listViews.remove(location);
                adapter.setListViews(listViews);
                back_bt.setText("" + Bimp.tempSelectBitmap.size() + " / 9");
                adapter.notifyDataSetChanged();
            }
        }
    }

    // 完成按钮的监听
    private class GallerySendListener implements OnClickListener {
        public void onClick(View v) {
            finish();
            intent.setClass(mContext, FollowWeChatPhotoActivity.class);
            startActivity(intent);
        }
    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            back_bt.setText("" + Bimp.tempSelectBitmap.size() + " / 9");
            send_bt.setPressed(true);
            send_bt.setClickable(true);
            send_bt.setTextColor(Color.WHITE);
        } else {
            send_bt.setPressed(false);
            send_bt.setClickable(false);
            send_bt.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }

    class MyPageAdapter extends PagerAdapter {

        private ArrayList<View> listViews;

        private int size;

        public MyPageAdapter(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public void setListViews(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public int getCount() {
            return size;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(listViews.get(position % size));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            try {
                container.addView(listViews.get(position % size), 0);
            } catch (Exception e) {
            }
            return listViews.get(position % size);
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

}
