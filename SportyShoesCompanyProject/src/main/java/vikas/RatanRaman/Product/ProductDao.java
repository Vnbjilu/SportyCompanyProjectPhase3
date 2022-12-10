package vikas.RatanRaman.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDao {
	
	@Autowired
	ProductRepository prdao;
	
	public void insertRecord(Product pd)
	{
		prdao.save(pd);
		
	}
	public List<Product> getAllRecord()
	{
		return prdao.findAll();
	}
	public String removePoductById(Long id)
	{
		prdao.deleteById(id);
		return "product id-> "+id+" is deleted Succesfully";
	}
}
