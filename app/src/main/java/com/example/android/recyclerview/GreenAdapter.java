/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;


/**
 * We couldn't come up with a good name for this class. Then, we realized
 * that this lesson is about RecyclerView.
 *
 * RecyclerView... Recycling... Saving the planet? Being green? Anyone?
 * #crickets
 *
 * Avoid unnecessary garbage collection by using RecyclerView and ViewHolders.
 *
 * If you don't like our puns, we named this Adapter GreenAdapter because its
 * contents are green.
 */
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NomsViewHolder> {

    //private static final String TAG = GreenAdapter.class.getSimpleName();
    private int mNumberItems; //attribute to indicate nmbr of intem to display in the RV
    private Context context;
    private List<String> names=Collections.emptyList();
    private final ListItemClickListener click_listener;

    public interface ListItemClickListener{
        void onListItemClick(String name);
    }

    public GreenAdapter(int numberOfItems, ListItemClickListener listener) {

        this.mNumberItems = numberOfItems;
        this.click_listener=listener;
        this.names=Arrays.asList(new String[]{"EL Mehdi","Fatima","Majid","Hakim","Omar","Salim","Hamza","Mouna","Salma","Mazalla","Aurélien","Vincent","Remi","Marc"});
    }
    @Override
    public NomsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NomsViewHolder viewHolder = new NomsViewHolder(view);

        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final NomsViewHolder holder, int position) {
        //Log.d(TAG, "#" + position);
        holder.bind(names.get(position));
        holder.nomsDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Tu as cliqué sur: " + holder.getName(), Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {

        return mNumberItems;
    }

    /**
     * Cache of the children views for a list item.
     */
    class NomsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView nomsDisplay;


        public NomsViewHolder(View itemView) {
            super(itemView);
            this.nomsDisplay= (TextView) itemView.findViewById(R.id.nomView);
            itemView.setOnClickListener(this);
        }

        public String getName(){
            return (String) nomsDisplay.getText();
        }
        void bind(String nom) {
            this.nomsDisplay.setText(nom);
        }

        @Override
        public void onClick(View view) {
            click_listener.onListItemClick(nomsDisplay.getText().toString());
        }


    }
}
