package sg.edu.rp.singlish;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import sg.edu.rp.singlish.Utils.LoadFiles;
import www.ning.com.ningdic.R;


public class FavoriteFragment extends Fragment {
    private List<String> list;
    private ListView lv;
    private BaseAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_page, null);

        lv = (ListView) view.findViewById(R.id.listview_favorite);

        list = new ArrayList<>();

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(getActivity());
                tv.setText(list.get(position));
                tv.setPadding(15,12,0,12);
                convertView = tv;
                return convertView;
            }
        };
        lv.setAdapter(adapter);


        List<Favorite> favData = DataSupport.findAll(Favorite.class);
        InputStream inputStream = getResources().openRawResource(R.raw.a);
        try {
            JSONObject obj = new JSONObject(LoadFiles.getString(inputStream));
            JSONArray jarry = obj.getJSONArray("items");
            for (int i = 0; i < jarry.length(); i++) {
                JSONObject subJSON = jarry.getJSONObject(i);
                String name = subJSON.getString("name");
                for (int j = 0; j < favData.size(); j++) {
                    if (favData.get(j).getName().equals(name)) {
                        list.add(name);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), DetailAty.class);
                intent.putExtra("keyWord", list.get(position));
                startActivity(intent);
            }
        });


        return view;




    }




}
