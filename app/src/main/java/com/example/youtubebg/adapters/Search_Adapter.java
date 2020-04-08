package com.example.youtubebg.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Callback;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.YoutubePlayerr;
import com.example.youtubebg.Views.Playlist_popup;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Search_Adapter extends RecyclerView.Adapter<Search_Adapter.MyViewHolder>  {

    private List<Search_Response.Item> results= new ArrayList<>();
    private Context context;
    private FragmentManager act = new FragmentManager() {
        @NonNull
        @Override
        public FragmentTransaction beginTransaction() {
            return null;
        }

        @Override
        public boolean executePendingTransactions() {
            return false;
        }

        @Nullable
        @Override
        public Fragment findFragmentById(int id) {
            return null;
        }

        @Nullable
        @Override
        public Fragment findFragmentByTag(@Nullable String tag) {
            return null;
        }

        @Override
        public void popBackStack() {

        }

        @Override
        public boolean popBackStackImmediate() {
            return false;
        }

        @Override
        public void popBackStack(@Nullable String name, int flags) {

        }

        @Override
        public boolean popBackStackImmediate(@Nullable String name, int flags) {
            return false;
        }

        @Override
        public void popBackStack(int id, int flags) {

        }

        @Override
        public boolean popBackStackImmediate(int id, int flags) {
            return false;
        }

        @Override
        public int getBackStackEntryCount() {
            return 0;
        }

        @NonNull
        @Override
        public BackStackEntry getBackStackEntryAt(int index) {
            return null;
        }

        @Override
        public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

        }

        @Override
        public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

        }

        @Override
        public void putFragment(@NonNull Bundle bundle, @NonNull String key, @NonNull Fragment fragment) {

        }

        @Nullable
        @Override
        public Fragment getFragment(@NonNull Bundle bundle, @NonNull String key) {
            return null;
        }

        @NonNull
        @Override
        public List<Fragment> getFragments() {
            return null;
        }

        @Nullable
        @Override
        public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment f) {
            return null;
        }

        @Override
        public boolean isDestroyed() {
            return false;
        }

        @Override
        public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb, boolean recursive) {

        }

        @Override
        public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb) {

        }

        @Nullable
        @Override
        public Fragment getPrimaryNavigationFragment() {
            return null;
        }

        @Override
        public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {

        }

        @Override
        public boolean isStateSaved() {
            return false;
        }
    };
    public Search_Adapter(List<Search_Response.Item> results, FragmentManager act, Context context)
    {
        this.results = results;
        this.act=act;
        this.context= context;
    }




    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_search,parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(results.get(position).getSnippet().getTitle());
        Picasso.get().load(results.get(position).getSnippet().getThumbnails().getDefault().getUrl()).into(holder.thumbnail);
        holder.add.setOnClickListener(e->{ openDialog(results.get(position)); });
        holder.thumbnail.setOnClickListener(e-> PlayVideo(position));



    }



    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public ImageButton add;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            thumbnail = itemView.findViewById(R.id.imageView);
            add = itemView.findViewById(R.id.add);




        }



    }
    private void openDialog(Search_Response.Item video) {
        Playlist_popup popup = new Playlist_popup(video);
        popup.show(act,"ezMPLE");

    }
    private void PlayVideo(int position) {
        Bundle bundl = new Bundle();
        bundl.putString("id",results.get(position).getId().getVideoId());
        Intent intent = new Intent(context, YoutubePlayerr.class);
        intent.putExtras(bundl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void updateList(List<Search_Response.Item> response)
    {
        this.results = response;
        notifyDataSetChanged();
    }
}