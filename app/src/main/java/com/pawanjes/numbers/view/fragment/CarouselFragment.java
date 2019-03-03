///*
//package com.pawanjes.a360.view.fragment;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.URLUtil;
//import android.widget.ImageView;
//import co.womaniya.app.R;
//import co.womaniya.app.service.model.ImageData;
//import co.womaniya.app.util.AuthUtil;
//import co.womaniya.app.util.Constants;
//import co.womaniya.app.util.ErrorHandler;
//import co.womaniya.app.util.GeneralUtils;
//import co.womaniya.app.view.activities.BaseActivity;
//import co.womaniya.app.view.activities.VideoDetailsActivity;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//
//import java.util.HashMap;
//
//public class CarouselFragment extends Fragment {
//
//    Context context;
//    ImageData imageData;
//    ImageView imageView;
//
//    public CarouselFragment() {
//    }
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            imageData = getArguments().getParcelable("imagedata");
//        }
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            this.context = context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
//        }
//    }
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.courosle_item, container, false);
//        imageView = v.findViewById(R.id.imageView);
//
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.color.colorTransparent);
//        requestOptions.error(R.drawable.image_not_found);
//        if (imageData != null)
//            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(imageData.getUrl()).into(imageView);
//
//        imageView.setOnClickListener(view -> {
//            String basePackage = "co.womaniya.app.view.activities.";
//            String activityName = basePackage + imageData.getBannerType();
//            if (URLUtil.isValidUrl(imageData.getBannerType())) {
//                String url = imageData.getBannerType();
//                Uri uri = Uri.parse(url);
//                String protocol = uri.getScheme();
//                if (protocol.equalsIgnoreCase("https")) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                    startActivity(intent);
//                }
//                if (url.contains("womaniya.co")
//                        && uri.getQueryParameterNames().contains("videoId")
//                        && uri.getQueryParameterNames().contains("channelId")) {
//                    Intent notificationIntent = new Intent(getActivity(), VideoDetailsActivity.class);
//                    notificationIntent.setData(uri);
//                    startActivity(notificationIntent);
//                    return;
//
//                }
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            } else {
//                try {
//                    Class<?> c = Class.forName(activityName);
//                    if (GeneralUtils.isLoginDone()) {
//                        Intent intent = new Intent(context, c);
//                        startActivity(intent);
//                    } else {
//                        AuthUtil.showLoginDialog(getActivity());
//                    }
//
//                } catch (ClassNotFoundException ignored) {
//                    ErrorHandler.INSTANCE.showAppUpdateDialog(context, imageData.getErrorMessage(), (dialogInterface, i) -> {
//                        final String appPackageName = context.getPackageName();
//                        try {
//                            startActivity(new Intent(Intent.ACTION_VIEW,
//                                    Uri.parse("market://details?id=" + appPackageName)));
//
//                        } catch (android.content.ActivityNotFoundException anfe) {
//                            startActivity(new Intent(Intent.ACTION_VIEW,
//                                    Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//                        }
//                    });
//                }
//            }
//
//
////            Bundle bundle = new Bundle();
////            bundle.putString(Constants.PARAM.BANNER_ID, String.valueOf(imageData.getId()));
////            bundle.putString(Constants.PARAM.BANNER_NAME, String.valueOf(imageData.getBannerType()));
//
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put(Constants.PARAM.BANNER_ID, String.valueOf(imageData.getId()));
//            hashMap.put(Constants.PARAM.BANNER_NAME, String.valueOf(imageData.getEventTrackingName()));
//
//            //((BaseActivity) getContext()).logEvent(Constants.EVENT.CLICK_ON_BANNER, bundle);
//            ((BaseActivity) getContext()).logEvent(Constants.EVENT.CLICK_ON_BANNER, hashMap);
//
//        });
//
//        return v;
//
//    }
//}
//*/
