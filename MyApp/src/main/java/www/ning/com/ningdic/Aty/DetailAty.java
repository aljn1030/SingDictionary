package www.ning.com.ningdic.Aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.InputStream;
import java.util.List;

import www.ning.com.ningdic.Bean.FavoriteBean;
import www.ning.com.ningdic.R;
import www.ning.com.ningdic.Utils.LoadFiles;


public class DetailAty extends AppCompatActivity {
    private TextView title, defi, content;
    private String keyWord;
    private ImageView iv, fav_star;
    private boolean check = false;
    private String thisTitle = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);
        Intent intent = getIntent();
        keyWord = intent.getStringExtra("keyWord");
        fav_star = (ImageView) findViewById(R.id.detail_fav);
        iv = (ImageView) findViewById(R.id.icon_back);
        title = (TextView) findViewById(R.id.detail_title);
        defi = (TextView) findViewById(R.id.detail_definition);
        content = (TextView) findViewById(R.id.detail_content);
        loadData();
        initEvent();
    }

    private void initEvent() {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailAty.this.finish();
            }
        });
        fav_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == true) {
                    fav_star.setImageResource(R.mipmap.without_star);
                    DataSupport.deleteAll(FavoriteBean.class, "name=?", thisTitle);
                    check = false;
                } else if (check == false) {
                    fav_star.setImageResource(R.mipmap.bein_star);
                    FavoriteBean favBean = new FavoriteBean();
                    favBean.setName(thisTitle);
                    favBean.save();
                    check = true;
                }
            }
        });
    }

    private void loadData() {
        InputStream inputStream = getResources().openRawResource(R.raw.a);
        try {
            JSONObject obj = new JSONObject(LoadFiles.getString(inputStream));
            JSONArray jarry = obj.getJSONArray("items");
            for (int i = 0; i <jarry.length() ; i++) {
                JSONObject subJSON = jarry.getJSONObject(i);
                String str_name = subJSON.getString("name");
                if (str_name.equals(keyWord)){
                    String str_defi = subJSON.getString("definition");
                    String str_content = subJSON.getString("content");
                    title.setText(str_name);
                    thisTitle = str_name;
                    defi.setText(str_defi);
                    content.setText(str_content);
                    //TODO 通过数据库判断是否收藏过
                    List<FavoriteBean> findTitle = DataSupport.where("name=?", thisTitle).find(FavoriteBean.class);

                    if (findTitle.size()==0) {
                        fav_star.setImageResource(R.mipmap.without_star);
                        check = false;
                    } else {
                        fav_star.setImageResource(R.mipmap.bein_star);
                        check = true;

                    }
                }

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
