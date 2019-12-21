package com.AK.CouponSystemPhase2.beans;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity(name="categories")
@Table
public class Category {

	private int id;
	private String name;
}
