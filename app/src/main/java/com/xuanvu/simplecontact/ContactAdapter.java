package com.xuanvu.simplecontact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    private List<Contact> mContacts;
    private Context mContext;
    private LayoutInflater mInflater;


    private final OnItemClickListener listener;

    public ContactAdapter(Context mContext, int mInflater, List<Contact> mContacts, OnItemClickListener listener) {
        this.mContacts = mContacts;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.listener = listener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.item_contact,
                viewGroup, false);
        return new ContactViewHolder(itemView);
    }


    public interface OnItemClickListener {
        void onItemClick(Contact mContacts);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int i) {
        final Contact contact = mContacts.get(i);
// holder.tvTitle.setText(contact.getmTitle());
        holder.tvFullname.setText(contact.getmFullname());

// holder.tvCompany.setText(contact.getmCompany());

        /*Sự kiện click vào item*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick( contact );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder{

        // TextView tvTitle;
        TextView tvFullname;
// TextView tvCompany;

        public ContactViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
// tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvFullname = (TextView) itemView.findViewById(R.id.tv_fullname);
// tvCompany = (TextView) itemView.findViewById(R.id.tv_company);
        }
    }
}