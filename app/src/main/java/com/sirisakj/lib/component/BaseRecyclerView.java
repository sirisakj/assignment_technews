package com.sirisakj.lib.component;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sirisakj.lib.bind.BaseBind;
import com.sirisakj.lib.component.im.IFakeBind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sirisak on 3/6/2559.
 */
@Deprecated
public abstract class BaseRecyclerView<CLASSBINDING extends ViewDataBinding, CLASSBIND extends BaseBind> extends RecyclerView {
    private ArrayList<CLASSBIND> mArrayList = new ArrayList<>();
    private BaseRecyclerViewListener<CLASSBINDING,CLASSBIND> mListener = null;



    private _InnerAdaptor mAdaptor;
    private DividerItemManager mDividerManager;

    public BaseRecyclerView(Context context) {
        super(context);
        initial(context);
    }

    public BaseRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial(context);
    }

    public BaseRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initial(context);
    }

    private void initial(Context context){
        if(isInEditMode()) {
            return;
        }

        setListener(null);


        setLayoutManager(getLayoutManager(context));

        mAdaptor = new _InnerAdaptor();
        setAdapter(mAdaptor);

        /*
        addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {

            }
        });//*/
        mDividerManager = new DividerItemManager();
        initialDividerItems(mDividerManager);
    }

    public DividerItemManager getDividerItemManger() { return mDividerManager; }
    public abstract RecyclerView.LayoutManager getLayoutManager(Context c);


    public void notifyDataSetChanged(){

        mAdaptor.notifyDataSetChanged();
    }

    public abstract int getLayoutId();

    public _InnerAdaptor getAdaptor(){ return mAdaptor; }

    public class _InnerViewHolder extends ViewHolder {
        private CLASSBINDING mCLASSBINDING;
        public _InnerViewHolder(CLASSBINDING CLASSBINDING) {
            super(CLASSBINDING.getRoot());

            mCLASSBINDING = CLASSBINDING;
        }

        public CLASSBINDING getBind() { return mCLASSBINDING; }
    }


    public class _InnerAdaptor extends RecyclerView.Adapter<_InnerViewHolder>{


        @Override
        public _InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CLASSBINDING viewBinding = inflateDataBinding(parent.getContext(),getLayoutId(), parent,viewType, true);

            return new _InnerViewHolder(viewBinding);
        }

        @Override
        public void onBindViewHolder(_InnerViewHolder holder, int position) {
            BaseRecyclerView.this.onBindViewHolder(holder.getBind(), BaseRecyclerView.this.getItemData(position), position);
        }

        @Override
        public int getItemCount() {
            return BaseRecyclerView.this.getItemCount();
        }
    }

    public CLASSBINDING inflateDataBinding(Context c, int layoutId, ViewGroup parent, int viewType, boolean attachToParent){
        LayoutInflater inflater = LayoutInflater.from(c);
        CLASSBINDING viewBinding = DataBindingUtil.inflate(inflater,layoutId, parent, attachToParent);
        return viewBinding;
    }

    public void onBindViewHolder(final CLASSBINDING bind,final CLASSBIND itemData,final int position){
        mListener.onBindViewHolder(bind, itemData, position);
    }

    public void onLoadMore(final int page,final int totalItemsCount){

    }

    public void reset(){
        mArrayList = new ArrayList<>();
        mAdaptor.notifyDataSetChanged();
    }

    public void addItem(CLASSBIND itemData){
        mArrayList.add(itemData);
    }

    public void addAllItemAndUpdate(List<CLASSBIND> items){
        mArrayList.addAll(items);
        mAdaptor.notifyDataSetChanged();
    }


    public void addAllItemAndUpdateFake(IFakeBind<CLASSBIND> fake, int size){
        for(int i=0;i<size;i++){
            addItem(fake.newInstance());
        }
        mAdaptor.notifyDataSetChanged();
    }

    public void addAllItemAndUpdateFake(IFakeBind<CLASSBIND> fake){
        addAllItemAndUpdateFake(fake,30);
    }

    public void addItemAndUpdate(CLASSBIND itemData){
        mArrayList.add(itemData);
        mAdaptor.notifyItemInserted(mArrayList.size() - 1);
    }

    /*
    public void removeItemAndUpdate(int position){
        mArrayList.remove(position);
        mAdaptor.notifyItemRemoved(position);


    }//*/

    public void removeItemAndUpdate(CLASSBIND item){
        int index = mArrayList.indexOf(item);
        mArrayList.remove(index);
        mAdaptor.notifyItemRemoved(index);
    }

    public void setListener(BaseRecyclerViewListener listener){
        if(listener == null){
            listener = new BaseRecyclerViewListener<CLASSBINDING,CLASSBIND>(){
                @Override
                public void onBindViewHolder(CLASSBINDING bind, CLASSBIND itemData, int position) {

                }
            };
        }
        mListener = listener;
    }

    public CLASSBIND getItemData(int position){
        return mArrayList.get(position);
    }

    public int getItemCount(){
        return mArrayList.size();
    }

    public interface BaseRecyclerViewListener<CLASSBINDING extends ViewDataBinding, CLASSBIND>  {
        public void onBindViewHolder(CLASSBINDING bind, CLASSBIND itemData, int position);
    }

    public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        // The minimum amount of items to have below your current scroll position
        // before loading more.
        private int visibleThreshold = 5;
        // The current offset index of data you have loaded
        private int currentPage = 0;
        // The total number of items in the dataset after the last load
        private int previousTotalItemCount = 0;
        // True if we are still waiting for the last reset of data to load.
        private boolean loading = true;
        // Sets the starting page index
        private int startingPageIndex = 0;

        private LinearLayoutManager mLinearLayoutManager;

        public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
            this.mLinearLayoutManager = layoutManager;
        }

        // This happens many times a second during a scroll, so be wary of the code you place here.
        // We are given a few useful parameters to help us work out if we need to load some more data,
        // but first we check if we are waiting for the previous load to finish.
        @Override
        public void onScrolled(RecyclerView view, int dx, int dy) {
            int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
            int visibleItemCount = view.getChildCount();
            int totalItemCount = mLinearLayoutManager.getItemCount();

            // If the total item count is zero and the previous isn't, assume the
            // list is invalidated and should be reset back to initial state
            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.loading = true;
                }
            }
            // If it’s still loading, we check to see if the dataset count has
            // changed, if so we conclude it has finished loading and update the current page
            // number and total item count.
            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }

            // If it isn’t currently loading, we check to see if we have breached
            // the visibleThreshold and need to reload more data.
            // If we do need to reload some more data, we execute onLoadMore to fetch the data.
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                currentPage++;
                onLoadMore(currentPage, totalItemCount);
                loading = true;
            }
        }

        // Defines the process for actually loading more data based on page
        public abstract void onLoadMore(int page, int totalItemsCount);

    }

    public abstract void initialDividerItems(DividerItemManager manager);
    public class DividerItemManager {
        public void add(RecyclerView.ItemDecoration divider){
            addItemDecoration(divider);
        }

        public void addDrawable(@DrawableRes int res){
            addItemDecoration(new DividerItemDecoration(getContext(), res));
        }
    }

}
