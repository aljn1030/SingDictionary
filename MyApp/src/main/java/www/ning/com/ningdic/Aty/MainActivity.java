package www.ning.com.ningdic.Aty;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.litepal.tablemanager.Connector;

import www.ning.com.ningdic.R;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView list, favorite;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        findView();
        initEvent();
        list.performClick();
        SQLiteDatabase db = Connector.getDatabase();
    }

    private void init() {
        manager = getFragmentManager();
    }

    private void initEvent() {
        list.setOnClickListener(this);
        favorite.setOnClickListener(this);
    }

    private void findView() {
        list = (ImageView) findViewById(R.id.list);
        favorite = (ImageView) findViewById(R.id.favorite);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list:
                favorite.setBackgroundColor(999999);
                list.setBackgroundColor(000000);
                FragmentTransaction transca1=manager.beginTransaction();
                ListFragment listFragment = new ListFragment();
                transca1.replace(R.id.fragments, listFragment, "list");
                transca1.commit();
                break;
            case R.id.favorite:
                list.setBackgroundColor(999999);
                favorite.setBackgroundColor(000000);
                FragmentTransaction transca2=manager.beginTransaction();
                FavoriteFragment favoriteFragment = new FavoriteFragment();
                transca2.replace(R.id.fragments, favoriteFragment, "favorite");
                transca2.commit();
                break;
        }
    }
}
