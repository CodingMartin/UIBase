package com.martin.framework.module.home.v;

import android.content.Intent;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martin.framework.R;
import com.martin.framework.base.BaseCompatActivity;
import com.martin.framework.module.home.c.MainContract;
import com.martin.framework.module.home.p.MainPresenter;
import com.martin.framework.module.splash.v.LoginActivity;
import com.martin.framework.module.test.PlaceholderActivity;

public class MainActivity extends BaseCompatActivity implements MainContract.View {

    private ListPopupWindow mListPopupWindow;
    private MainContract.Presenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化视图
     */
    @Override
    protected void bindView() {
    }

    /**
     * 事件绑定
     */
    @Override
    protected void bindEvent() {
    }

    @Override protected boolean hasBackButton() {
        return super.hasBackButton();
    }

    /**
     * 数据绑定
     */
    @Override
    protected void bindData() {
        new MainPresenter(this);
    }

    @Override
    protected boolean isUseSystemUI() {
        return true;
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more_toolbar,menu);
        return true;
    }

    public void showToast(View view) {
        showToast("Hello world");
    }

    public void showProgress(View view) {
        showProgress();
    }

    public void showMessageDialog(View view) {
        showMessageDialog("Hello World");
    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void showPop(View view) {
       startActivity(new Intent(this, PlaceholderActivity.class));
//        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
//        ActivityCompat.startActivity(this, new Intent(this, PlaceholderActivity.class), compat.toBundle());
    }


    static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private OnItemClickListener mItemClickListener;

        public void setOnItemClickListener(OnItemClickListener li) {
            mItemClickListener = li;
        }

        @Override
        public Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_menu_item, parent, false);
            return new Holder(item);
        }

        @Override
        public void onBindViewHolder(final Adapter.Holder holder, int position) {
            holder.tv.setText("item " + position);
            if(mItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onItemClick(holder.getLayoutPosition(),
                                holder.tv.getText().toString());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return 50;
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView tv;
            public Holder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }

        interface OnItemClickListener {
            void onItemClick(int position, String text);
        }
    }

}
