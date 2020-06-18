package com.leadevs.misslab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leadevs.misslab.R;
import com.leadevs.misslab.models.Asisten;

import java.util.List;

public class AsistenGridViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Asisten> mData;

    public AsistenGridViewAdapter(Context context, List<Asisten> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_asisten, null);
        }

        TextView TVNamaLengkap = (TextView) convertView.findViewById(R.id.item_asisten_namalengkap);
        TVNamaLengkap.setText(mData.get(position).getNamaLengkap());

        return convertView;
    }
}
