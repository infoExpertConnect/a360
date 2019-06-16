package com.pawanjes.numbers.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pawanjes.numbers.R;
import com.pawanjes.numbers.service.model.ImageData;

public class CarouselFragment extends Fragment {

    Context context;
    ImageData imageData;
    ImageView imageView;

    public CarouselFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageData = getArguments().getParcelable("imagedata");
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.context = context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.courosle_item, container, false);
        imageView = v.findViewById(R.id.imageView);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bg);
        requestOptions.error(R.drawable.bg);
        if (imageData != null)
            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(imageData.getUrl()).into(imageView);

        return v;

    }
}
