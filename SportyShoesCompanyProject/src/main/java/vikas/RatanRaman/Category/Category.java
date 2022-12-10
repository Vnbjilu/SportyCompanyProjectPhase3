package vikas.RatanRaman.Category;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import vikas.RatanRaman.Product.Product;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long catId;
	
	@Column(nullable = false,unique = true)
	private String catType;
	
	@OneToMany(mappedBy = "cat")
	List<Product> product=new ArrayList<>();
	

	

	public void addProduct(Product product) {
		this.product.add(product);
		
	}
	public void removeProduct(Product product)
	{
		this.product.remove(product);
	}

	public Category(String catType) {
		super();
		this.catType = catType;
	}

	public Category() {
		super();
	}

	public String getCatType() {
		return catType;
	}

	public void setCatType(String catType) {
		this.catType = catType;
	}

	public long getCatId() {
		return catId;
	}
	
	

}
