package cn.huntercat.lsmapp.demo.viewpager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private List<ImageView> viewList = new ArrayList<>();
    private List<GoodsInfo> goodsInfoList = new ArrayList<>();

    public ImagePagerAdapter(Context context, List<GoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
        for (int i = 0; i < goodsInfoList.size(); i++) {
            ImageView view = new ImageView(context);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT));
            view.setImageResource(goodsInfoList.get(i).pic);
            viewList.add(view);
        }
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return goodsInfoList.get(position).name;
    }
}
