package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Products implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idProduct;
    private String name;
    private Integer price;
    private Integer stock;
    private LocalDate startCatalogue;
    private LocalDate endingCatalogue;
    
	public Products(Integer idProduct, String name, Integer price, Integer stock, LocalDate startCatalogue,LocalDate endingCatalogue) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.startCatalogue = startCatalogue;
		this.endingCatalogue = endingCatalogue;
	}

	
	
	public Integer getIdProduct() {
		return idProduct;
	}



	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public Integer getStock() {
		return stock;
	}



	public void setStock(Integer stock) {
		this.stock = stock;
	}



	public LocalDate getStartCatalogue() {
		return startCatalogue;
	}



	public void setStartCatalogue(LocalDate startCatalogue) {
		this.startCatalogue = startCatalogue;
	}



	public LocalDate getEndingCatalogue() {
		return endingCatalogue;
	}



	public void setEndingCatalogue(LocalDate endingCatalogue) {
		this.endingCatalogue = endingCatalogue;
	}



	@Override
	public String toString() {
		return "Products [idProduct=" + idProduct + ", name=" + name + ", price=" + price + ", stock=" + stock
				+ ", startCatalogue=" + startCatalogue + ", endingCatalogue=" + endingCatalogue + "]";
	}
    
    
}
