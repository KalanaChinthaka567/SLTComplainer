package com.example.slt.MainScreens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.slt.Usage.DailyUsage;
import com.example.slt.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Usage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Usage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Usage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView speed;
    private TextView tvDate;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat dateFormat;
    Calendar myCalendar = Calendar.getInstance();

    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tvPercentage;

    int pStatusTotal = 0;
    private Handler handlerTotal = new Handler();
    TextView tvPercentageTotal;
    private Button btnDailyUsage;

    private OnFragmentInteractionListener mListener;

    public Usage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Usage.
     */
    // TODO: Rename and change types and number of parameters
    public static Usage newInstance(String param1, String param2) {
        Usage fragment = new Usage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_usage,
                container, false);


//        Initializing Views
        speed = (TextView) view.findViewById(R.id.speed);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) view.findViewById(R.id.circularProgressbar);
        final ProgressBar mProgressTotal = (ProgressBar) view.findViewById(R.id.circularProgressbar1);
        btnDailyUsage = (Button) view.findViewById(R.id.btnDailyUsage);
        btnDailyUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DailyUsage.class);
                startActivity(intent);
            }
        });


        String a = "Your speed is";
        String b = "Normal";
        String c = "right now";
        Spannable spanna = new SpannableString(b);
        spanna.setSpan(new BackgroundColorSpan(R.color.white_greyish), 0, b.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        speed.setText(a + " " + spanna + " " + c);


        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy - MMM - dd");
        Date myDate = new Date();
        String filename = timeStampFormat.format(myDate);

        SimpleDateFormat timeStampFormat1 = new SimpleDateFormat("HH:mm a");
        Date myDate1 = new Date();
        String filename1 = timeStampFormat1.format(myDate1);


        tvDate = (TextView) view.findViewById(R.id.date);
        tvDate.setText("Remaining volume as of " + filename + " " + filename1.toUpperCase());


        tvPercentage = (TextView) view.findViewById(R.id.tvPercentage);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                pStatus = 60;

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        mProgress.setProgress(pStatus);
                        tvPercentage.setText(pStatus + "%");

                    }
                });
                try {
                    // Sleep for 200 milliseconds.
                    // Just to display the progress slowly
                    Thread.sleep(16); //thread will take approx 3 seconds to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        tvPercentageTotal = (TextView) view.findViewById(R.id.tvPercentage1);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                pStatusTotal = 20;

                handlerTotal.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        mProgressTotal.setProgress(pStatusTotal);
                        tvPercentageTotal.setText(pStatusTotal + "%");

                    }
                });
                try {
                    // Sleep for 200 milliseconds.
                    // Just to display the progress slowly
                    Thread.sleep(16); //thread will take approx 3 seconds to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
