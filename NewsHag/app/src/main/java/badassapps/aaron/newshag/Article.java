package badassapps.aaron.newshag;

/**
 * Created by austin on 6/1/16.
 */
public class Article {

    String URL = new String ("");
    String ID = new String ("");
    String TITLE = new String ("");

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
