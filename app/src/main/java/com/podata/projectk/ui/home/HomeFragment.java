package com.podata.projectk.ui.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.podata.projectk.MyAdapter;
import com.podata.projectk.R;
import com.podata.projectk.item;
import com.podata.projectk.setting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private final ArrayList<item> list = new ArrayList<>();
    final Bundle bundle = new Bundle();
    private SwipeRefreshLayout swipe1;
    String[] URL_data, listSize_data,listSize_unit, district_data, facility_data, name_data, address_data, visitTime_data, visitTime2_data, disinfection_data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        URL_data = getResources().getStringArray(R.array.URl1);
        district_data = getResources().getStringArray(R.array.district);
        facility_data = getResources().getStringArray(R.array.facility);
        name_data = getResources().getStringArray(R.array.brand);;
        address_data = getResources().getStringArray(R.array.address);;
        visitTime_data = getResources().getStringArray(R.array.visitTime);;
        visitTime2_data = getResources().getStringArray(R.array.visitTime2);;
        disinfection_data = getResources().getStringArray(R.array.disinfection);
        listSize_data =  getResources().getStringArray(R.array.list_size);;
        listSize_unit = getResources().getStringArray(R.array.unit);

        new Description().execute();

        View v = inflater.inflate(R.layout.fragment_home, container, false);


        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe1 = (SwipeRefreshLayout)view.findViewById(R.id.swipe1);
        swipe1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                new Description().execute();
                swipe1.setRefreshing(false);
            }
        });
    }



    private class Description extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            int location = Integer.parseInt(setting.getConfigValue(getContext(),"location"));
//            System.out.println("ddddddddddd"+setting.getConfigValue(getContext(),"location"));

            try {
//                System.out.println("테스트 "+Jsoup.connect(URL_data[1]).get().select("div [id=tab2]").text());

//                System.out.println("테스트"+Jsoup.connect(URL_data[2]).get().select("div [class=table_scroll scroll_x] table[class=corona]").text());
                Document doc = Jsoup.connect(URL_data[location]).get();
                Elements elements = doc.select("div.content table tbody");
                System.out.println("sadsad"+elements);
                Elements mElementDataSize = doc.select(listSize_data[location]).select(listSize_unit[location]);
//                System.out.println(doc.select(listSize_data[location]).select(listSize_unit[location]));
                for(Element elem : mElementDataSize) {
                    String district = elem.select(district_data[location]).text();
                    String facility = elem.select(facility_data[location]).text();
                    String name = elem.select(name_data[location]).text();
                    String address = elem.select(address_data[location]).text();
                    String visitTime = elem.select(visitTime_data[location]).text();

                    String visitTime2 = elem.select(visitTime2_data[location]).text();
                    String disinfection = elem.select(disinfection_data[location]).text();

                    System.out.println("dddddddddddddddddddddddddddddddd"+location+mElementDataSize+district+facility+name+address+visitTime);

                    System.out.println("주소"+address);

                    if(!name.equals("") && !name.contains("역학조사"))
                        list.add(new item(district, facility, name, address, visitTime, visitTime2, disinfection));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerview);
            MyAdapter myAdapter = new MyAdapter(getActivity(),list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);
        }
    }
}