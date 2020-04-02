package com.example.youtubebg;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.Views.Fragment_search;
import com.example.youtubebg.Views.Playlists;
import com.example.youtubebg.adapters.Search_Adapter;
import com.example.youtubebg.retrofit.Retrofit1;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Fragment_search.searchresult {
    TextView text;
    Search_Response list = new Search_Response();
    RecyclerView recyclerView;
    private FragmentManager finalPopup;
    private TextView textView;
    private Search_Adapter adapter;
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
FragmentManager popup = new FragmentManager() {
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

popup = this.getSupportFragmentManager();

         finalPopup = popup;
                recyclerView = findViewById(R.id.a);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);






    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){

            getMenuInflater().inflate(R.layout.menu, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            switch (id) {
                case R.id.playlist: {
                    Intent i = new Intent(this, Playlists.class);
                    startActivity(i);
                    break;
                }

            }
            return super.onOptionsItemSelected(item);
        }


    @Override
    public void search(String search) {
    }
}
