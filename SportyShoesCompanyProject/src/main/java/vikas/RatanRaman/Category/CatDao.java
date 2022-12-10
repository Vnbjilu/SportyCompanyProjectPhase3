package vikas.RatanRaman.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CatDao {
	
	@Autowired
	CategoryRepository Cr;
	
	public void insert(Category ce)
	{
		Cr.save(ce);
		
	}
	public List<Category> GetAll()
	{
		return Cr.findAll();
	}
	public String deleteCat(Long id)
	{
		Cr.deleteById(id);
		return "Cat id="+id+" is deleted succesfully";
	}
}
