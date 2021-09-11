package com.podata.projectk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<item> mList;


    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView_title;
        private final TextView textView_facility;
        private final TextView title_location;
        private final TextView textView_visit;



        public ViewHolder(View itemView) {
            super(itemView);

            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            textView_facility =  (TextView) itemView.findViewById(R.id.textView_facility);
            title_location = (TextView) itemView.findViewById(R.id.title_location);
            textView_visit = (TextView) itemView.findViewById(R.id.textView_visit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos =getAdapterPosition();
                    int status = NetworkStatus.getConnectivityStatus(mContext.getApplicationContext());
                    if(status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI){
                        if(pos != RecyclerView.NO_POSITION){
                            Intent intent = new Intent(mContext,DetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("title", String.valueOf(mList.get(pos)));
                            intent.putExtra("name", String.valueOf(mList.get(pos).getName()));
                            intent.putExtra("address", String.valueOf(mList.get(pos).getAddress()));
                            intent.putExtra("visitTime", String.valueOf(mList.get(pos).getVisitTime2()));

                            mContext.startActivity(intent);
                        }
                    }else {
                        Toast.makeText(mContext.getApplicationContext(), "네트워크 상태를 확인해주세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    //생성자
    public MyAdapter(Context context, ArrayList<item> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        int color = position % 3;
//
//        switch (String.valueOf(mList.get(position).getFacility())){
//            case "기숙사":
//                holder.itemView.setBackgroundResource(R.drawable.btn_green);
//                break;
//            case "교육기관":
//                holder.itemView.setBackgroundResource(R.drawable.btn_blue);
//                break;
//            case "음식점":
//                holder.itemView.setBackgroundResource(R.drawable.btn_orange);
//                break;
//        }
        String colorSelect = String.valueOf(mList.get(position).getFacility());
        System.out.println(colorSelect);
        if(colorSelect.equals("음식점")||colorSelect.equals("커피숍")){
            holder.itemView.setBackgroundResource(R.drawable.btn_orange);
        }else if(colorSelect.equals("교육기관")||colorSelect.equals("공공시설")){
            holder.itemView.setBackgroundResource(R.drawable.btn_blue);
        }else if(colorSelect.equals("유흥시설")||colorSelect.equals("PC방")||colorSelect.equals("노래방")){
            holder.itemView.setBackgroundResource(R.drawable.btn_red);
        }else
            holder.itemView.setBackgroundResource(R.drawable.btn_green);


        if (!mList.get(position).getName().equals("")){
            holder.textView_title.setText(String.valueOf(mList.get(position).getTitle()));
            holder.textView_facility.setText(String.valueOf(mList.get(position).getFacility()));
            holder.title_location.setText(String.valueOf(mList.get(position).getName()));
            holder.textView_visit.setText(String.valueOf(mList.get(position).getVisitTime()));
        }
    }

    @Override
    public int getItemCount() {
        System.out.println("리스트크기"+mList.size());
        return mList.size();
//
    }
}