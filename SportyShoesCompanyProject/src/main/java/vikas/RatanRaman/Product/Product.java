package vikas.RatanRaman.Product;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import vikas.RatanRaman.Category.Category;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long prodId;
	
	private String prodName;
	private String prodCompany;
	private int prodPrice;
	private Date prodCreated;
	
	@ManyToOne
	Category cat;
	
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	public Product(String prodName, String prodCompany, int prodPrice, Date prodCreated) {
		super();
		this.prodName = prodName;
		this.prodCompany = prodCompany;
		this.prodPrice = prodPrice;
		this.prodCreated = prodCreated;
	}
	public Product() {
		super();
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdCompany() {
		return prodCompany;
	}
	public void setProdCompany(String prodCompany) {
		this.prodCompany = prodCompany;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public Date getProdCreated() {
		return prodCreated;
	}
	public void setProdCreated(Date prodCreated) {
		this.prodCreated = prodCreated;
	}
	public long getProdId() {
		return prodId;
	}
	
	
	

}
