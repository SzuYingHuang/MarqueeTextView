package com.cindyhuang.marqueetextview;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MarqueeTextAdapter extends RecyclerView.Adapter<MarqueeTextAdapter.ViewHolder> {

    private List<String> dataset;
    private float textSize;
    private int textColor;
    private boolean isBold;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public MarqueeTextAdapter(List<String> dataset, float textSize, int textColor, boolean isBold) {
        this.dataset = dataset;
        this.textSize = textSize;
        this.textSize = textSize;
        this.textColor = textColor;
        this.isBold = isBold;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = new TextView(parent.getContext());
        tv.setMaxLines(1);
        return new ViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int widthParam = (position > 0 && position < dataset.size() - 1) ?
                ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT;

        TextView tv = (TextView) holder.view;
        tv.setLayoutParams(new ViewGroup.LayoutParams(widthParam, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setText(dataset.get(position));
        tv.setTextSize(textSize);
        tv.setTextColor(textColor);
        tv.setTypeface(isBold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        tv.setIncludeFontPadding(false);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}
