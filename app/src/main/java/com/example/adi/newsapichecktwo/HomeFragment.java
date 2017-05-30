package com.example.adi.newsapichecktwo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    int[] news_img={R.drawable.i1,R.drawable.i2,R.drawable.i3};
    String title[],author[],description[];
    ArrayList<News> newslist=new ArrayList<News>();
    //Volley Credentials
    public String API_KEY="c8f2022f4dca49ce845bbebfa0e47afa";
    public String SOURCE="techcrunch";
    public String SORT_PARAM="latest";
    public String url="https://newsapi.org/v1/articles?source="+SOURCE+"&sortBy="+SORT_PARAM+"&apiKey="+API_KEY;

    ProgressDialog pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        title=getResources().getStringArray(R.array.title);
        author=getResources().getStringArray(R.array.author);
        description=getResources().getStringArray(R.array.discription);
        final int[] count = {0};
        /*for(String titl:title){
            News new_news=new News(news_img[count],titl,author[count],description[count]);
            newslist.add(new_news);
            count++;
        }*/
        pDialog = new ProgressDialog(this.getContext());
        pDialog.setMessage("Wait for a moment...");
        pDialog.show();
        JsonObjectRequest jr=new JsonObjectRequest(Request.Method.GET,url,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject json_data;
                    String title,auth,desc,img_url;
                    ArrayList<String> a=new ArrayList<String>();
                    ArrayList<String> t=new ArrayList<String>();
                    ArrayList<String> iu=new ArrayList<String>();
                    ArrayList<String> d=new ArrayList<String>();
                    JSONArray jArray=response.getJSONArray("articles");
                    for(int i=0;i<jArray.length();i++){
                        json_data = jArray.getJSONObject(i);
                        auth=json_data.getString("author");
                        title=json_data.getString("title");
                        desc=json_data.getString("description");
                        img_url=json_data.getString("urlToImage");
                        a.add(auth);
                        t.add(title);
                        d.add(desc);
                        iu.add(img_url);
                    }
                    int count=0;
                    int nsize=t.size();
                    for(int i=count;i<nsize;i++){
                        News new_n=new News(iu.get(i),t.get(i),a.get(i),d.get(i));
                        newslist.add(new_n);
                    }
                    pDialog.dismiss();
                    adapter=new HeadlineAdapter(newslist, getContext());
                    layoutManager=new LinearLayoutManager(getContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppConnector.getInstance().addToRequestQueue(jr);


        return view;
    }

}
