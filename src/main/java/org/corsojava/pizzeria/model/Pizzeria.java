package org.corsojava.pizzeria.model;

import java.math.BigDecimal;
import java.text.NumberFormat.Style;

import org.apache.el.lang.ELArithmetic.BigDecimalDelegate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity

public class Pizzeria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "il nome non può essere nullo")
	@NotEmpty(message = "Il nome non deve essere vuoto")
	@Size(min = 5, max = 13, message = "non può essere meno di 5 caratteri o più di 13.")
	private String name;
	@DecimalMin("1.00")
	@NotNull(message = "il prezzo non può essere nullo")
	private BigDecimal price;
	private String photo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public @DecimalMin("1.00") BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Pizzeria [id=" + id + ", name=" + name + ", price=" + price + ", photo=" + photo + "]";
	}

}
