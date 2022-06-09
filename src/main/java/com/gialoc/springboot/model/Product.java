package com.gialoc.springboot.model;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String image;
	private int categoryid;
	private String short_description;
	private String full_description;
	private double price;
	private double price_discount;
	private int typeid;
	private String slug;

	private int brandid;
	private Date created_on_utc;
	private Date updated_on_utc;
	private int discount;
	private String manufacturer;
	private int article_number;
	private String delivery_time;
	private String availabilty;
	private String trademark;
	private String product_number;

	
	private String origin;
	private String machine;
	private String dial_thickness;
	private String dial_diameter;
	private String glasses;
	private String strap;
	private int waterproof;
	private String especially;
	private String insurance;
	private String reviews;
	
	private String pair;
	private String delivery;
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String address;
	private int sold;
	private String cate;
	
	public Product() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getShort_description() {
		return short_description;
	}
	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}
	public String getFull_description() {
		return full_description;
	}
	public void setFull_description(String full_description) {
		this.full_description = full_description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice_discount() {
		return price_discount;
	}
	public void setPrice_discount(double price_discount) {
		this.price_discount = price_discount;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public int getBrandid() {
		return brandid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public Date getCreated_on_utc() {
		return created_on_utc;
	}
	public void setCreated_on_utc(Date created_on_utc) {
		this.created_on_utc = created_on_utc;
	}
	public Date getUpdated_on_utc() {
		return updated_on_utc;
	}
	public void setUpdated_on_utc(Date updated_on_utc) {
		this.updated_on_utc = updated_on_utc;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getArticle_number() {
		return article_number;
	}
	public void setArticle_number(int article_number) {
		this.article_number = article_number;
	}
	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}
	public String getAvailabilty() {
		return availabilty;
	}
	public void setAvailabilty(String availabilty) {
		this.availabilty = availabilty;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}
	public String getProduct_number() {
		return product_number;
	}
	public void setProduct_number(String product_number) {
		this.product_number = product_number;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public String getDial_thickness() {
		return dial_thickness;
	}
	public void setDial_thickness(String dial_thickness) {
		this.dial_thickness = dial_thickness;
	}
	public String getDial_diameter() {
		return dial_diameter;
	}
	public void setDial_diameter(String dial_diameter) {
		this.dial_diameter = dial_diameter;
	}
	public String getGlasses() {
		return glasses;
	}
	public void setGlasses(String glasses) {
		this.glasses = glasses;
	}
	public String getStrap() {
		return strap;
	}
	public void setStrap(String strap) {
		this.strap = strap;
	}
	public int getWaterproof() {
		return waterproof;
	}
	public void setWaterproof(int waterproof) {
		this.waterproof = waterproof;
	}
	public String getEspecially() {
		return especially;
	}
	public void setEspecially(String especially) {
		this.especially = especially;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public String getPair() {
		return pair;
	}
	public void setPair(String pair) {
		this.pair = pair;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public String getImg4() {
		return img4;
	}
	public void setImg4(String img4) {
		this.img4 = img4;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", categoryid=" + categoryid
				+ ", short_description=" + short_description + ", full_description=" + full_description + ", price="
				+ price + ", price_discount=" + price_discount + ", typeid=" + typeid + ", slug=" + slug + ", brandid="
				+ brandid + ", created_on_utc=" + created_on_utc + ", updated_on_utc=" + updated_on_utc + ", discount="
				+ discount + ", manufacturer=" + manufacturer + ", article_number=" + article_number
				+ ", delivery_time=" + delivery_time + ", availabilty=" + availabilty + ", trademark=" + trademark
				+ ", product_number=" + product_number + ", origin=" + origin + ", machine=" + machine
				+ ", dial_thickness=" + dial_thickness + ", dial_diameter=" + dial_diameter + ", glasses=" + glasses
				+ ", strap=" + strap + ", waterproof=" + waterproof + ", especially=" + especially + ", insurance="
				+ insurance + ", reviews=" + reviews + ", pair=" + pair + ", delivery=" + delivery + ", img1=" + img1
				+ ", img2=" + img2 + ", img3=" + img3 + ", img4=" + img4 + ", address=" + address + ", sold=" + sold
				+ ", cate=" + cate + "]";
	}
}
