package com.example.handphonedetail.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.handphonedetail.Model.Handphone;
import com.example.handphonedetail.R;

import java.util.ArrayList;

public class ListPhoneAdapter extends RecyclerView.Adapter<ListPhoneAdapter.PhoneViewHolder> {

    public ArrayList<Handphone> getListHandphone() {
        return listHandphone;
    }

    public void setListHandphone(ArrayList<Handphone> listHandphone) {
        this.listHandphone = listHandphone;
    }

    private ArrayList<Handphone> listHandphone;

    public ListPhoneAdapter(ArrayList<Handphone> listHandphone) {
        this.listHandphone = listHandphone;
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_phone_list,viewGroup,false);
        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder phoneViewHolder, int i) {
        Handphone handphone = getListHandphone().get(i) ;

        Glide.with(phoneViewHolder.itemView.getContext())
                .load(handphone.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(phoneViewHolder.imgPhoto);

        phoneViewHolder.tvName.setText(handphone.getNama());
        phoneViewHolder.tvPrice.setText(handphone.getHarga());
    }

    @Override
    public int getItemCount() {
        return getListHandphone().size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvPrice;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvPrice = itemView.findViewById(R.id.tv_item_price);
        }
    }
}
