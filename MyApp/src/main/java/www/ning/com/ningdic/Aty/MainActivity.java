package www.ning.com.ningdic.Aty;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import www.ning.com.ningdic.R;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView list, favorite;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getFragmentManager();
        list = (ImageView) findViewById(R.id.list);
        favorite = (ImageView) findViewById(R.id.favorite);
        list.setOnClickListener(this);
        favorite.setOnClickListener(this);
        list.performClick();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list:

                FragmentTransaction transac1=manager.beginTransaction();
                ListFragment listFragment = new ListFragment();
                transac1.replace(R.id.fragments, listFragment, "list");
                transac1.commit();
                break;
            case R.id.favorite:

                FragmentTransaction transac2=manager.beginTransaction();
                FavoriteFragment favoriteFragment = new FavoriteFragment();
                transac2.replace(R.id.fragments, favoriteFragment, "favorite");
                transac2.commit();
                break;
        }
    }
}
