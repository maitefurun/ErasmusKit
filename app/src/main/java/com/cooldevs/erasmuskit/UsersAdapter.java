package com.cooldevs.erasmuskit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maite on 11/04/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>  implements View.OnClickListener {

    private ArrayList<User> usersList;
    private View.OnClickListener listener;


    public UsersAdapter(ArrayList<User> users) {
        this.usersList=users;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent,false);
        itemView.setOnClickListener(this);

        return new UsersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {

        User user = usersList.get(position);
        holder.bindUser(user);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {

        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }




    public class UsersViewHolder extends RecyclerView.ViewHolder{

        private TextView userName;
        private TextView userNationality;

        public UsersViewHolder(View itemView) {
            super(itemView);

            userName= (TextView) itemView.findViewById(R.id.user_list_name);
            userNationality= (TextView) itemView.findViewById(R.id.user_list_nationality);
        }

        void bindUser(final User user){
            userName.setText(user.getUserName());
            userNationality.setText(user.getNationality());
        }

    }

}