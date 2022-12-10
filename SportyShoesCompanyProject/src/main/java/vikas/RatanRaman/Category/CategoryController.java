package vikas.RatanRaman.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vikas.RatanRaman.User.UserController;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryRepository catr;
	
	@GetMapping("/")
	public ModelAndView showIndex(ModelAndView mv)
	{
		mv.setViewName("/category/index");
		mv.addObject("login", "logout");
		mv=UserController.login_check(mv);
		
		return mv;
	}
	
	@GetMapping("/addcat")
	public ModelAndView getFrm(ModelAndView mv)
	{
		mv.setViewName("category/catfrm");
		mv=UserController.login_check(mv);
		return mv;
		//return "category/catfrm";
		
	}
	@PostMapping("/addcat")
	public ModelAndView insert(HttpServletRequest request,HttpServletResponse response,ModelAndView mv)
	{
		Category cat=new Category();
		
		cat.setCatType(request.getParameter("catName"));
		catr.save(cat);
		
		mv.setViewName("redirect:category/showcat");
		mv=UserController.login_check(mv);	
		return mv;
		
	}
	@GetMapping("/showcat")
	public ModelAndView showcategory(ModelAndView mv)
	{
		List<Category> cat=catr.findAll();
		mv.addObject("Category", cat);
		mv.setViewName("category/showcat");
		mv=UserController.login_check(mv);
		return mv;
		
	}
	

}
