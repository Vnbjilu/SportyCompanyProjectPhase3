package vikas.RatanRaman.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
	public Optional<Category> getRecordById(Long id)
	{
		return Cr.findById(id);
		
	}
	public String deleteById(Long id)
	{
		Cr.deleteById(id);
		return "Category is deleted Successfully cat id="+id;
	}
}
