package davidtgnewsproject.qq986945193.com.davidtgnewsproject.pullRecycleView.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import davidtgnewsproject.qq986945193.com.davidtgnewsproject.R;
import davidtgnewsproject.qq986945193.com.davidtgnewsproject.app.MyApplication;
import davidtgnewsproject.qq986945193.com.davidtgnewsproject.pullRecycleView.BaseListAdapter;
import davidtgnewsproject.qq986945193.com.davidtgnewsproject.pullRecycleView.BaseViewHolder;
import davidtgnewsproject.qq986945193.com.davidtgnewsproject.pullRecycleView.DividerItemDecoration;
import davidtgnewsproject.qq986945193.com.davidtgnewsproject.pullRecycleView.PullRecycler;
import davidtgnewsproject.qq986945193.com.davidtgnewsproject.pullRecycleView.layoutmanager.ILayoutManager;
import davidtgnewsproject.qq986945193.com.davidtgnewsproject.pullRecycleView.layoutmanager.MyLinearLayoutManager;
import davidtgnewsproject.qq986945193.com.davidtgnewsproject.utils.OkHttpUtils;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public abstract class BaseListFragment<T> extends Fragment implements PullRecycler.OnRecyclerRefreshListener {
    protected BaseListAdapter adapter;
    protected ArrayList<T> mDataList;
    protected PullRecycler recycler;
    protected OkHttpUtils okHttpUtils = MyApplication.getApp().getOkHttpUtils();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = (PullRecycler) view.findViewById(R.id.pullRecycler);
        setUpAdapter();
        recycler.setOnRefreshListener(this);
        recycler.setLayoutManager(getLayoutManager());
        recycler.addItemDecoration(getItemDecoration());
        recycler.setAdapter(adapter);
    }

    protected void setUpAdapter() {
        adapter = new ListAdapter();
    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getContext());
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getContext(), R.drawable.list_divider);
    }

    public class ListAdapter extends BaseListAdapter {

        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
        }

        @Override
        protected int getDataCount() {
            return mDataList != null ? mDataList.size() : 0;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        public boolean isSectionHeader(int position) {
            return BaseListFragment.this.isSectionHeader(position);
        }
    }

    protected boolean isSectionHeader(int position) {
        return false;
    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

}
