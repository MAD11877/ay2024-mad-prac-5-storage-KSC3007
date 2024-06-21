package sg.edu.np.mad.madpractical5;
import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public String getName() {return name;}
    public void setName(String username) {this.name = username;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public Boolean isFollowed() {return followed;}
    public void setFollowed(Boolean followed) {this.followed = followed;}

    public User(String name, String description, int id, boolean followed) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }
}

