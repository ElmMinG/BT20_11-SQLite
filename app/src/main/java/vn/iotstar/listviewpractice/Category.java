package vn.iotstar.listviewpractice;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("images")
    private String images;

    @SerializedName("description")
    private String description;

    // Constructor, Getter, Setter
    public int getId() { return id; }
    public String getName() { return name; }
    public String getImages() { return images; }
    public String getDescription() { return description; }
}