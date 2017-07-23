package www.ning.com.ningdic.Bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by win10 on 2017/1/8.
 */
public class FavoriteBean extends DataSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
