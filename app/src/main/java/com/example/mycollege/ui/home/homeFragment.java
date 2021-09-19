package com.example.mycollege.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mycollege.R;

import com.smarteist.autoimageslider.SliderView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment {

    SliderView sliderView;

    TextView phone_dial, telphone_dial, sendmail;

    private ImageView map;

    ImageButton arrow,bbaarrow,bcomarrow;

    LinearLayout hiddenView,bbahiddenView,bcomhiddenView;

    CardView cardView,bbacardView,bcomcardView;

    LinearLayout fybcasyllabus, sybcasyllabus, tybcasyllabus,fybbasyllabus, sybbasyllabus, tybbasyllabus,fybcomsyllabus, sybcomsyllabus, tybcomsyllabus;

    private mySliderAdapter adapter;

    String url1 = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/college.jpg?alt=media&token=70a3b4bb-397d-440e-aabf-4a905e02dca3";

    String url2 = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/university.png?alt=media&token=c66ca8bf-1e49-4e33-8d54-8b4e01fb99c9";

    String url3 = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/Library.jpg?alt=media&token=179a4bea-e314-4b98-878b-5798eccf47b5";

    String url4 = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/event_image_1.jpg?alt=media&token=01dc3015-8b73-4c42-a02c-72e16c33acd6";

    String url5 = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/event_image_3.jpg?alt=media&token=61c8a0fa-7c9a-4de6-b98a-a7473ed5a64f";

    String fybca = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/fybbasyllabus.pdf?alt=media&token=caac0fed-d013-4b7a-9c89-00517d87c040";
    String sybca = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/sybcasyllabus.pdf?alt=media&token=d5215aab-6104-4f7b-90ee-0ef4d995431b";
    String tybca = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/tybcasyllabus.pdf?alt=media&token=923dedd0-fd80-4437-9c37-172bbcb2cd94";

    String fybba = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/fybbasyllabus.pdf?alt=media&token=caac0fed-d013-4b7a-9c89-00517d87c040";
    String sybba = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/sybbasyllabus.pdf?alt=media&token=9bf6685e-d05d-4423-8439-57b1c16abb4e";
    String tybba = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/tybcasyllabus.pdf?alt=media&token=923dedd0-fd80-4437-9c37-172bbcb2cd94";

    String fybcom = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/fybcomsyllabus.pdf?alt=media&token=a57b7367-a04d-4b5d-a9b3-f96cf0b66743";
    String sybcom = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/fybcomsyllabus.pdf?alt=media&token=a57b7367-a04d-4b5d-a9b3-f96cf0b66743";
    String tybcom = "https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/tybcomsyllabus.pdf?alt=media&token=7233d50e-1c5d-4244-9fa3-75939d9c8f7e";


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        List<SliderData> sliderDataArrayList = new ArrayList<>();
        phone_dial = view.findViewById(R.id.phone_dial);
        telphone_dial = view.findViewById(R.id.telephone_dial);
        sendmail = view.findViewById(R.id.sendmail);

        cardView = view.findViewById(R.id.base_cardview);
        arrow = view.findViewById(R.id.arrow_button);
        hiddenView = view.findViewById(R.id.hidden_view);

        bbacardView = view.findViewById(R.id.bbabase_cardview);
        bbaarrow = view.findViewById(R.id.bbaarrow_button);
        bbahiddenView = view.findViewById(R.id.bbahidden_view);

        bcomcardView = view.findViewById(R.id.bcombase_cardview);
        bcomarrow = view.findViewById(R.id.bcomarrow_button);
        bcomhiddenView = view.findViewById(R.id.bcomhidden_view);

        fybcasyllabus = view.findViewById(R.id.bcaSyllabus);
        sybcasyllabus = view.findViewById(R.id.bcaNotice);
        tybcasyllabus = view.findViewById(R.id.bcaEbook);

        fybbasyllabus = view.findViewById(R.id.bbafySyllabus);
        sybbasyllabus = view.findViewById(R.id.bbasySyllabus);
        tybbasyllabus = view.findViewById(R.id.bbatySyllabus);

        fybcomsyllabus = view.findViewById(R.id.bcomfySyllabus);
        sybcomsyllabus = view.findViewById(R.id.bcomsySyllabus);
        tybcomsyllabus = view.findViewById(R.id.bcomtySyllabus);

        map = view.findViewById(R.id.map);
        SliderView sliderView = view.findViewById(R.id.slider);
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));
        sliderDataArrayList.add(new SliderData(url4));
        sliderDataArrayList.add(new SliderData(url5));
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMap();
            }
        });
        phone_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = "7043437581";
                dial(number);
            }
        });
        telphone_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tnumber = "0261-2277739";
                dial(tnumber);
            }
        });
        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"himanshusharma704@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Inquiry");
                email.putExtra(Intent.EXTRA_TEXT, "Hello Sir/mam Inquiry related to : ");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });


        mySliderAdapter adapter = new mySliderAdapter(getActivity(), sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    Log.e("Call","more");
                } else {

                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
                    Log.e("Call","less");

                }
            }
        });
        bbaarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (bbahiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(bbacardView,
                            new AutoTransition());
                    bbahiddenView.setVisibility(View.GONE);
                    bbaarrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
                } else {

                    TransitionManager.beginDelayedTransition(bbacardView,
                            new AutoTransition());
                    bbahiddenView.setVisibility(View.VISIBLE);
                    bbaarrow.setImageResource(R.drawable.ic_baseline_expand_less_24);

                }
            }
        });
        bcomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (bcomhiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(bcomcardView,
                            new AutoTransition());
                    bcomhiddenView.setVisibility(View.GONE);
                    bcomarrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
                } else {

                    TransitionManager.beginDelayedTransition(bcomcardView,
                            new AutoTransition());
                    bcomhiddenView.setVisibility(View.VISIBLE);
                    bcomarrow.setImageResource(R.drawable.ic_baseline_expand_less_24);

                }
            }
        });

        fybcasyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    viewpdf(fybca);
            }
        });
        sybcasyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewpdf(sybca);

            }

        });
        tybcasyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpdf(tybca);

            }
        });

        fybbasyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpdf(fybba);
            }
        });
        sybbasyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewpdf(sybba);

            }

        });
        tybcasyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpdf(tybba);

            }
        });

        fybcomsyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpdf(fybcom);
            }
        });
        sybcomsyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewpdf(sybcom);

            }

        });
        tybcomsyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpdf(tybcom);

            }
        });
        return view;
    }
    private void viewpdf(String link){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }


    private void dial(String number) {

        Intent dial = new Intent(Intent.ACTION_DIAL);
        dial.setData(Uri.parse("tel:" + number));
        startActivity(dial);
    }

    private void openMap() {

        Uri uri = Uri.parse("geo:0,0?q=Udhna Citizen college Surat Gujarat");
        Intent tomap = new Intent(Intent.ACTION_VIEW, uri);
        tomap.setPackage("com.google.android.apps.maps");
        startActivity(tomap);

    }

}
