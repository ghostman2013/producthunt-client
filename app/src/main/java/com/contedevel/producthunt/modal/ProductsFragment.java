package com.contedevel.producthunt.modal;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contedevel.producthunt.BR;
import com.contedevel.producthunt.client.Post;
import com.contedevel.producthunt.client.ServiceApi;
import com.contedevel.producthunt.databinding.FragmentProductsBinding;
import com.contedevel.producthunt.databinding.ItemProductBinding;
import com.contedevel.producthunt.util.RecyclerConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductsFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Post[]> {

    private static final String KEY_TOPIC_ID = "topic_id";

    private ProductsAdapter mAdapter = new ProductsAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentProductsBinding binding = FragmentProductsBinding
                .inflate(inflater, container, false);
        ViewModel model = buildModel(getContext());
        binding.setModel(model);

        return binding.getRoot();
    }

    private ViewModel buildModel(Context context) {
        RecyclerConfig config = new RecyclerConfig.Builder()
                .setHasFixedSize(true)
                .setLayoutManager(new LinearLayoutManager(context))
                .setAdapter(mAdapter)
                .build();
        return new ViewModel(config, false);
    }

    public void reload(int topicId) {
        Bundle args = new Bundle();
        args.putInt(KEY_TOPIC_ID, topicId);
        getLoaderManager().restartLoader(0, args, this);
    }

    @NonNull
    @Override
    public Loader<Post[]> onCreateLoader(int id, Bundle args) {

        if (args == null || !args.containsKey(KEY_TOPIC_ID)) {
            throw new IllegalArgumentException("Bundle doesn't contain a topic ID");
        }

        int topicId = args.getInt(KEY_TOPIC_ID);
        Context context = getContext();
        assert context != null;
        return new PostsLoader(context, topicId);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Post[]> loader, Post[] data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Post[]> loader) {

    }

    private static class PostsLoader extends AsyncTaskLoader<Post[]> {

        private ServiceApi mApi = new ServiceApi();
        private int mTopicId;

        PostsLoader(@NonNull Context context, int topicId) {
            super(context);
            mTopicId = topicId;
        }

        @Nullable
        @Override
        public Post[] loadInBackground() {
            return mApi.getPosts(mTopicId);
        }
    }

    public static class ViewModel extends BaseObservable {
        private final RecyclerConfig mRecyclerConfig;
        private boolean mHasItems;

        ViewModel(RecyclerConfig config, boolean hasItems) {
            mRecyclerConfig = config;
            mHasItems = hasItems;
        }

        @Bindable
        public RecyclerConfig getRecyclerConfig() {
            return mRecyclerConfig;
        }

        @Bindable()
        public boolean isHasItems() {
            return mHasItems;
        }

        public void setHasItems(boolean hasItems) {
            mHasItems = hasItems;
            notifyPropertyChanged(BR.hasItems);
        }
    }

    public static class ProductViewModel {

        private final String mTitle;
        private final String mDescription;
        private final int mVotes;
        private final String mThumbnailUrl;

        ProductViewModel(String title, String description,
                         int votes, @Nullable String thumbnailUrl) {
            mTitle = title;
            mDescription = description;
            mVotes = votes;
            mThumbnailUrl = thumbnailUrl;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getDescription() {
            return mDescription;
        }

        public int getVotes() {
            return mVotes;
        }

        public String getThumbnailUrl() {
            return mThumbnailUrl;
        }
    }

    private static class ProductsAdapter
            extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
        private List<ProductViewModel> mItems = new ArrayList<>();

        public void add(ProductViewModel item) {
            mItems.add(item);
        }

        public void addAll(Collection<? extends ProductViewModel> collection) {
            mItems.addAll(collection);
        }

        public void clear() {
            mItems.clear();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ViewHolder(ItemProductBinding
                    .inflate(inflater, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setModel(mItems.get(position));
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private ItemProductBinding mBinding;

            ViewHolder(ItemProductBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }

            void setModel(ProductViewModel model) {
                mBinding.setModel(model);
            }
        }
    }
}
