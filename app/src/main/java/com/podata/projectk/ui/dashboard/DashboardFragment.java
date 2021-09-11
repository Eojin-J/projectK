package com.podata.projectk.ui.dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.podata.projectk.R;
import com.podata.projectk.setting;
import com.podata.projectk.ui.home.HomeFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class DashboardFragment extends Fragment {

    TextView korea_total, total_num, release_num, isolate_num, rip_num, total_before_num, release_before_num, isolate_before_num, rip_before_num;
    TextView baseDate;
    final Bundle bundle = new Bundle();
    private SwipeRefreshLayout swipe2;
    String[] URL_data, basetime_data, K_total, total_data, release_data, isolate_data, rip_data;
    String[] B_total, B_release_data, B_isolate_data, B_rip_data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        URL_data= getResources().getStringArray(R.array.URl2);;
        basetime_data= getResources().getStringArray(R.array.basetime);
        K_total = getResources().getStringArray(R.array.korea_total);
        total_data= getResources().getStringArray(R.array.total);;
        release_data= getResources().getStringArray(R.array.release);;
        isolate_data= getResources().getStringArray(R.array.isolate);;
        rip_data= getResources().getStringArray(R.array.rip);;
        B_total= getResources().getStringArray(R.array.total_before);;
        B_release_data= getResources().getStringArray(R.array.release_before);;
        B_isolate_data = getResources().getStringArray(R.array.isolate_before);;
        B_rip_data= getResources().getStringArray(R.array.rip_before);;


        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        int location = Integer.parseInt(setting.getConfigValue(getContext(),"location"));
            new Thread() {
                @Override
                public void run() {
                    Document doc = null;
                    try {
                        System.out.println("테스트"+Jsoup.connect(URL_data[3]).get().select(total_data[3]).text());
                        doc = Jsoup.connect(URL_data[location]).get();
                        //div [class=contents] (div아래에 있는 항목): nth-child(n) (앞의 항목의 아래에 있는 항목):nth-child(n) (변수명)
                        String k_total = doc.select(K_total[location]).text();
                        String baseDate = doc.select(basetime_data[location]).text();
                        String total = doc.select(total_data[location]).text();
                        String text2 = doc.select(release_data[location]).text();
                        String text3 = doc.select(isolate_data[location]).text();
                        String text4 = doc.select(rip_data[location]).text();

                        String text5 = doc.select(B_total[location]).text();
                        String text6 = doc.select(B_release_data[location]).text();
                        String text7 = doc.select(B_isolate_data[location]).text();
                        String text8 = doc.select(B_rip_data[location]).text();

                        bundle.putString("k_total", k_total);
                        bundle.putString("base", baseDate);
                        bundle.putString("total", total);
                        bundle.putString("text2", text2);
                        bundle.putString("text3", text3);
                        bundle.putString("text4", text4);

                        bundle.putString("text5", text5);
                        bundle.putString("text6", text6);
                        bundle.putString("text7", text7);
                        bundle.putString("text8", text8);

                        Message msg = handler.obtainMessage();
                        msg.setData(bundle);
                        handler.sendMessage(msg);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();



        return v;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int location = Integer.parseInt(setting.getConfigValue(getContext(),"location"));
        swipe2 = (SwipeRefreshLayout)view.findViewById(R.id.swipe2);
        swipe2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread() {
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            doc = Jsoup.connect(URL_data[location]).get();
                            System.out.println("테스트"+Jsoup.connect(URL_data[3]).get().select(basetime_data[3]).text());
                            //div [class=contents] (div아래에 있는 항목): nth-child(n) (앞의 항목의 아래에 있는 항목):nth-child(n) (변수명)
                            String k_total = doc.select(K_total[location]).text();
                            String baseDate = doc.select(basetime_data[location]).text();
                            String total = doc.select(total_data[location]).text();
                            String text2 = doc.select(release_data[location]).text();
                            String text3 = doc.select(isolate_data[location]).text();
                            String text4 = doc.select(rip_data[location]).text();

                            String text5 = doc.select(B_total[location]).text();
                            String text6 = doc.select(B_release_data[location]).text();
                            String text7 = doc.select(B_isolate_data[location]).text();
                            String text8 = doc.select(B_rip_data[location]).text();

                            bundle.putString("k_total", k_total);
                            bundle.putString("base", baseDate);
                            bundle.putString("total", total);
                            bundle.putString("text2", text2);
                            bundle.putString("text3", text3);
                            bundle.putString("text4", text4);

                            bundle.putString("text5", text5);
                            bundle.putString("text6", text6);
                            bundle.putString("text7", text7);
                            bundle.putString("text8", text8);

                            Message msg = handler.obtainMessage();
                            msg.setData(bundle);
                            handler.sendMessage(msg);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                swipe2.setRefreshing(false);
            }
        });
    }


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
                korea_total = (TextView)getView().findViewById(R.id.korea_total);
                total_num = (TextView) getView().findViewById(R.id.total_num);
                release_num = (TextView) getView().findViewById(R.id.release_num);
                isolate_num = (TextView) getView().findViewById(R.id.isolate_num);
                rip_num = (TextView) getView().findViewById(R.id.rip_num);
                total_before_num = (TextView) getView().findViewById(R.id.total_before_num);
                release_before_num = (TextView) getView().findViewById(R.id.release_before_num);
                isolate_before_num = (TextView) getView().findViewById(R.id.isolate_before_num);
                rip_before_num = (TextView) getView().findViewById(R.id.rip_before_num);
                baseDate = (TextView) getView().findViewById(R.id.baseDate);

                korea_total.setText(bundle.getString("k_total"));
                baseDate.setText(bundle.getString("base"));

                total_num.setText(bundle.getString("total"));
                release_num.setText(bundle.getString("text2"));
                isolate_num.setText(bundle.getString("text3"));
                rip_num.setText(bundle.getString("text4"));

                total_before_num.setText(bundle.getString("text5"));
                release_before_num.setText(bundle.getString("text6"));
                isolate_before_num.setText(bundle.getString("text7"));
                rip_before_num.setText(bundle.getString("text8"));

            return false;
        }
    });

}