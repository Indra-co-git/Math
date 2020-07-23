package com.indra.math;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(@NonNull Context context, ArrayList<User> users) {
        super(context, 0,users );

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.user_item, parent, false);
        }

        User u = getItem(position);

        TextView rank = listItemView.findViewById(R.id.pos);
        rank.setText(String.valueOf(position+1));

        TextView namet =listItemView.findViewById(R.id.name);
        namet.setText(u.getEmail());

        TextView usernamet = listItemView.findViewById(R.id.score);
        usernamet.setText(u.getScore());

        return listItemView;

    }
}
