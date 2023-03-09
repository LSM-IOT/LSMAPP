package cn.huntercat.lsmapp.demo.viewpager;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import cn.huntercat.lsmapp.R;

public class DemoViewPagerActivity extends AppCompatActivity {

    private List<GoodsInfo> goodsInfoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_viewpager);

        goodsInfoList = GoodsInfo.getDefaultList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, goodsInfoList);
        ViewPager vpContent = findViewById(R.id.vp_content);
        vpContent.setAdapter(adapter);
        vpContent.setCurrentItem(0);
        vpContent.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(DemoViewPagerActivity.this, "您翻到的手机品牌是：" + goodsInfoList.get(position).name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
