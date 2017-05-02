package cn.edu.neusoft.fooddemo.bean;

public class Order {
	private int order_id;
	private int user_id;
	private int food_id;
	private String username;
	private String foodname;
	
	 private int num;
	 private double sum;	 
	 private String suggesttime;
	 private String ordertime;
	 private String shopname;
	 private String shopaddress;
	 private double price;
	 private String content;
	 private String comment_time;
	 
	 
	 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getShopaddress() {
		return shopaddress;
	}
	public void setShopaddress(String shopaddress) {
		this.shopaddress = shopaddress;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public String getSuggesttime() {
		return suggesttime;
	}
	public void setSuggesttime(String suggesttime) {
		this.suggesttime = suggesttime;
	}
	@Override
	public String toString() {
		return "Order [user_id=" + user_id + ", food_id=" + food_id
				+ ", username=" + username + ", foodname=" + foodname
				+ ", num=" + num + ", sum=" + sum + ", suggesttime="
				+ suggesttime + "]";
	}
	

	
}
