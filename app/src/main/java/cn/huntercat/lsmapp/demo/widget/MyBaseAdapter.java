package cn.huntercat.lsmapp.demo.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.huntercat.lsmapp.R;

/**
 * 自定义适配器
 */
public class MyBaseAdapter extends BaseAdapter {

    private Context context; // 声明一个上下文对象
    private List<Planet> planetList;

    public MyBaseAdapter(Context context, List<Planet> planetList) {
        this.context = context;
        this.planetList = planetList;
    }

    /**
     * 获取列表项个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return planetList.size();
    }

    /**
     * 获取列表项的数据
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return
     */
    @Override
    public Object getItem(int position) {
        return planetList.get(position);
    }

    /**
     * 获取列表项的编号
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取指定位置的列表项视图
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            holder.ivIconItemList = convertView.findViewById(R.id.iv_icon_item_list);
            holder.tvNameItemList = convertView.findViewById(R.id.tv_name_item_list);
            holder.tvDescItemList = convertView.findViewById(R.id.tv_desc_item_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Planet planet = planetList.get(position);
        holder.ivIconItemList.setImageResource(planet.image);
        holder.tvNameItemList.setText(planet.name);
        holder.tvDescItemList.setText(planet.desc);
        return convertView;
    }

    public final class ViewHolder {
        public ImageView ivIconItemList;
        public TextView tvNameItemList;
        public TextView tvDescItemList;
    }


}
