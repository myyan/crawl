package stock.crawl.model;

import java.io.Serializable;

/**
 * Created by heiqie on 2017/2/9.
 */
public class User implements Serializable{
    private static final long serialVersionUID = -213221189192962074L;

    private int id;
    private String name;
    private int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
