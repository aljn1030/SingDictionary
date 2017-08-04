package sg.edu.rp.singlish;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;


public class Favorite extends DataSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
