package www.ning.com.ningdic.Aty;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import www.ning.com.ningdic.R;
import www.ning.com.ningdic.Utils.LoadFiles;

/**
 * Created by win10 on 2017/1/8.
 */
public class ListFragment extends Fragment {
    private List<String> list;
    private BaseAdapter adapter;
    private EditText et;
    private ImageView searchbutton;
    private View view;
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_page, null);
        findView();
        loadData();
        init();
        initEvent();
        return view;
    }

    private void loadData() {
        list = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.a);
        try {
            JSONObject obj = new JSONObject(LoadFiles.getString(inputStream));
            JSONArray jarry = obj.getJSONArray("items");
            for (int i = 0; i < jarry.length(); i++) {
                JSONObject subJSON = jarry.getJSONObject(i);
                String name = subJSON.getString("name");
                list.add(name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void init() {
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
                tv.setPadding(10, 12, 0, 12);
                convertView = tv;
                return convertView;
            }
        };
        lv.setAdapter(adapter);
    }


    private void findView() {
        lv = (ListView) view.findViewById(R.id.listview_list);
        searchbutton = (ImageView) view.findViewById(R.id.list_search);
        et = (EditText) view.findViewById(R.id.et_list);
    }

    private void initEvent() {
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et.getText())) {
                    Toast.makeText(getActivity(), "请输入", Toast.LENGTH_SHORT).show();
                } else {
                    String a = new String();
                    for (int i = 0; i < list.size(); i++) {
                        if (et.getText().toString().equals(list.get(i))) {
                            a = list.get(i);
                            list.clear();
                            list.add(a);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailAty.class);
                intent.putExtra("keyWord", list.get(position));
                startActivity(intent);
            }
        });
    }


}
