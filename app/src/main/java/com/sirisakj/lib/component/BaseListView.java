package com.sirisakj.lib.component;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.sirisakj.lib.bind.BaseBind;
import com.sirisakj.lib.component.im.BaseListViewListener;
import com.sirisakj.lib.component.im.IFakeBind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sirisak on 3/6/2559.
 */
public abstract class BaseListView<VIEW extends View, BIND extends BaseBind> extends ListView implements NestedScrollingChild {
    private ArrayList<BIND> mArrayList = new ArrayList<>();
    private BaseListViewListener<VIEW, BIND> mListener = null;


    private _InnerAdaptor mAdaptor;
    private NestedScrollingChildHelper mScrollingChildHelper;

    public BaseListView(Context context) {
        super(context);
        if (isInEditMode())
            return;
        initial(context);
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode())
            return;
        initial(context);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode())
            return;
        initial(context);
    }

    protected void initial(Context context) {

        mScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);

        setListener(null);


        mAdaptor = new _InnerAdaptor();
        setAdapter(mAdaptor);

        /*
        addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {

            }
        });//*/
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        return mScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }


    public void notifyDataSetChanged() {

        mAdaptor.notifyDataSetChanged();
    }


    public _InnerAdaptor getAdaptor() {
        return mAdaptor;
    }




    public class _InnerAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return BaseListView.this.getItemCount();
        }

        @Override
        public BIND getItem(int position) {
            return BaseListView.this.getItemData(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);

            return BaseListView.this.getView(position, type, convertView, parent);
        }
    }

    protected View getView(int position, int type, View convertView, ViewGroup parent) {
        VIEW VIEW = null;
        if (convertView == null) {
            VIEW = BaseListView.this.inflateDataBinding(parent.getContext(), parent, type, false);
            convertView = VIEW;

        } else {
            VIEW = (VIEW) convertView;
        }
        BaseListView.this.rowInitial(parent.getContext(), VIEW, BaseListView.this.getItemData(position), type, position);
        return convertView;
    }

    private VIEW inflateDataBinding(Context c, ViewGroup parent, int viewType, boolean attachToParent) {
        return onInflateDataBinding(c, parent, attachToParent);
    }

    public abstract VIEW onInflateDataBinding(Context c, ViewGroup parent, boolean attachToParent);

    private void rowInitial(Context c, final VIEW bind, final BIND itemData, int viewType, final int position) {
        onRowInitial(bind, itemData, viewType, position);
        mListener.onRowInitial(bind, itemData, position);
    }

    public abstract void onRowInitial(final VIEW VIEW, final BIND bind, int viewType, final int position);


    public void onLoadMore(final int page, final int totalItemsCount) {

    }

    public void reset() {
        mArrayList = new ArrayList<>();
        mAdaptor.notifyDataSetChanged();
    }

    public void removeAllItems() {
        mArrayList = new ArrayList<>();
    }

    public void addItem(BIND itemData) {
        mArrayList.add(itemData);
    }

    public void addAllItemAndUpdate(List<BIND> items) {
        mArrayList.addAll(items);
        mAdaptor.notifyDataSetChanged();
    }


    public void addAllItemAndUpdateFake(IFakeBind<BIND> fake) {
        addAllItemAndUpdateFake(fake, 30);
    }

    public void addAllItemAndUpdateFake(IFakeBind<BIND> fake, int size) {
        for (int i = 0; i < size; i++) {
            addItem(fake.newInstance());
        }
        mAdaptor.notifyDataSetChanged();
    }

    public void addItemAndUpdate(BIND itemData) {
        mArrayList.add(itemData);
        mAdaptor.notifyDataSetChanged();
    }

    /*
    public void removeItemAndUpdate(int position){
        mArrayList.remove(position);
        mAdaptor.notifyItemRemoved(position);


    }//*/

    public void removeItemAndUpdate(BIND item) {
        int index = mArrayList.indexOf(item);
        mArrayList.remove(index);
        notifyDataSetChanged();
    }

    public void setListener(BaseListViewListener<VIEW, BIND> listener) {
        if (listener == null) {
            listener = new BaseListViewListener<VIEW, BIND>() {
                @Override
                public void onRowInitial(VIEW bind, BIND itemData, int position) {

                }
            };
        }
        mListener = listener;
    }

    public BIND getItemData(int position) {
        return mArrayList.get(position);
    }

    public int getItemCount() {
        return mArrayList.size();
    }
    public  ArrayList<BIND> getItems() {
        return mArrayList;
    }

}
