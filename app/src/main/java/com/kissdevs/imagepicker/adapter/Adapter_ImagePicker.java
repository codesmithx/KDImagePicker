package com.kissdevs.imagepicker.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kissdevs.imagepicker.R;
import com.kissdevs.imagepicker.listeners.OnImageClickListener;
import com.kissdevs.imagepicker.model.Picker_Image;

import java.util.ArrayList;
import java.util.List;

public class Adapter_ImagePicker extends RecyclerView.Adapter<Adapter_ImagePicker.ImageViewHolder> {

    private List<Picker_Image> images = new ArrayList<>();
    private List<Picker_Image> selectedImages;

    private Context context;
    private LayoutInflater inflater;
    private OnImageClickListener itemClickListener;

    public Adapter_ImagePicker(Context context, List<Picker_Image> selectedImages, OnImageClickListener itemClickListener) {
        this.context = context;
        this.selectedImages = selectedImages;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.ef_imagepicker_item_image, parent, false);
        return new ImageViewHolder(itemView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder viewHolder, int position) {

        Picker_Image image = images.get(position);

        Glide.with(context)
                .load(image.getPath())
                .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder))
                .into(viewHolder.imageView);

        if (isSelected(image)) {
            viewHolder.alphaView.setAlpha(0.5f);
            if (Build.VERSION.SDK_INT >= 23)
                viewHolder.itemView.setForeground(ContextCompat.getDrawable(context, R.drawable.ic_done_white));
            else
                viewHolder.itemView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_done_white));
        } else {
            viewHolder.alphaView.setAlpha(0.0f);

            if (Build.VERSION.SDK_INT >= 23)
                viewHolder.itemView.setForeground(null);
            else
                viewHolder.itemView.setBackgroundDrawable(null);
        }

    }

    private boolean isSelected(Picker_Image image) {
        for (Picker_Image selectedImage : selectedImages) {
            if (selectedImage.getPath().equals(image.getPath())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public void setData(List<Picker_Image> images) {
        this.images.clear();
        this.images.addAll(images);
    }

    public void addAll(List<Picker_Image> images) {
        int startIndex = this.images.size();
        this.images.addAll(startIndex, images);
        notifyItemRangeInserted(startIndex, images.size());
    }

    public void addSelected(Picker_Image image) {
        selectedImages.add(image);
        notifyItemChanged(images.indexOf(image));
    }

    public void removeSelectedImage(Picker_Image image) {
        selectedImages.remove(image);
        notifyItemChanged(images.indexOf(image));
    }

    public void removeSelectedPosition(int position, int clickPosition) {
        selectedImages.remove(position);
        notifyItemChanged(clickPosition);
    }

    public void removeAllSelectedSingleClick() {
        selectedImages.clear();
        notifyDataSetChanged();
    }

    public Picker_Image getItem(int position) {
        return images.get(position);
    }

    public List<Picker_Image> getSelectedImages() {
        return selectedImages;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private View alphaView;
        private final OnImageClickListener itemClickListener;

        public ImageViewHolder(View itemView, OnImageClickListener itemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            alphaView = itemView.findViewById(R.id.view_alpha);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            view.setSelected(true);
            itemClickListener.onClick(view, getAdapterPosition());
        }
    }


}
