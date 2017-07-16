package com.sirisakj.technews.component.bind;

import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;

import com.sirisakj.lib.bind.BaseBind;

/**
 * Created by icsme on 15-Jul-17.
 */

public class FeedViewBind extends BaseBind {
    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> author = new ObservableField<>();
    public final ObservableField<String> link = new ObservableField<>();


    public void onClickLink(View view){
        String url = link.get();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        view.getContext().startActivity(i);
    }
}
