package yzh.com.a1020_rxjavaandretrofit.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import yzh.com.a1020_rxjavaandretrofit.R;
import yzh.com.a1020_rxjavaandretrofit.info.RoomBean;

/**
 * RecyclerView的适配器
 * Created by HP on 2016/10/20.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context context;
    private List<RoomBean> list;

    public RecyclerViewAdapter(Context context,List<RoomBean> list) {
        this.list = list;
        this.context=context;
    }
    @Override
    public int getItemCount() {
        Log.e("Tag" ,"Size" + list.size());
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RoomBean roomBean = list.get(position);
        holder.textView.setText(roomBean.getRoomName());
        Uri uri=Uri.parse(roomBean.getVerticalSrc());
        holder.draweeView.setImageURI(uri);
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);

            draweeView= (SimpleDraweeView) itemView.findViewById(R.id.drawee_view);
            textView= (TextView) itemView.findViewById(R.id.text);
        }
    }
}
