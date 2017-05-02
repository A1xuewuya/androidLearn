package cn.edu.neusoft.fooddemo.bean;

/**
 * Created by liningning on 2016/4/5.
 */
public class Food {
    private int food_id;
    private String foodname;
    private double price;
    private String intro;
    private String pic;
    private int shop_id;
    private int type_id;
    private int recommand;

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRecommand() {
        return recommand;
    }

    public void setRecommand(int recommand) {
        this.recommand = recommand;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "Food{" +
                "food_id=" + food_id +
                ", foodname='" + foodname + '\'' +
                ", price=" + price +
                ", intro='" + intro + '\'' +
                ", pic='" + pic + '\'' +
                ", shop_id=" + shop_id +
                ", type_id=" + type_id +
                ", recommand=" + recommand +
                '}';
    }
}
