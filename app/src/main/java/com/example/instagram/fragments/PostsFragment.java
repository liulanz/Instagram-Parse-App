package com.example.instagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagram.Post;
import com.example.instagram.PostsAdapter;
import com.example.instagram.R;
import com.example.instagram.databinding.FragmentComposeBinding;
import com.example.instagram.databinding.FragmentPostsBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class PostsFragment extends Fragment {
    FragmentPostsBinding fragmentPostsBinding;
    protected List<Post> allPosts;
    private  static final String TAG = "PostsFragment";
    protected PostsAdapter adapter;
    public PostsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        fragmentPostsBinding = FragmentPostsBinding.bind(view);
        fragmentPostsBinding.rvPosts.setAdapter(adapter);

        fragmentPostsBinding.rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
        fragmentPostsBinding.swiperContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                queryPosts();
            }
        });
        // Configure the refreshing colors
        fragmentPostsBinding.swiperContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.include(Post.KEY_USER);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e!=null){
                    Log.e(TAG, "issue with getting posts",e);
                    return;
                }
                for(Post post: posts){
                    Log.i(TAG, "Post"+post.getDescription()+" username: "+ post.getUser().getUsername());
                }
                adapter.clear();
                adapter.addAll(posts);
                fragmentPostsBinding.swiperContainer.setRefreshing(false);

            }
        });
    }

}