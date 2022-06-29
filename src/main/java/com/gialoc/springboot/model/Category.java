package com.gialoc.springboot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String image;
	private String slug;
	private Date created_on_utc;
	private Date update_on_utc;
	
	public Category() {
		super();
	}

	public Category(String name, String image, String slug, Date created_on_utc, Date update_on_utc) {
		super();
		this.name = name;
		this.image = image;
		this.slug = slug;
		this.created_on_utc = created_on_utc;
		this.update_on_utc = update_on_utc;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Date getCreated_on_utc() {
		return created_on_utc;
	}
	public void setCreated_on_utc(Date created_on_utc) {
		this.created_on_utc = created_on_utc;
	}
	public Date getUpdate_on_utc() {
		return update_on_utc;
	}
	public void setUpdate_on_utc(Date update_on_utc) {
		this.update_on_utc = update_on_utc;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", image=" + image + ", slug=" + slug + ", created_on_utc="
				+ created_on_utc + ", update_on_utc=" + update_on_utc + "]";
	}
}
