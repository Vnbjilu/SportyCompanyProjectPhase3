package vikas.RatanRaman.Product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import vikas.RatanRaman.Category.CatDao;
import vikas.RatanRaman.Category.Category;
import vikas.RatanRaman.User.UserController;

@Controller
@RequestMapping("/Product")
public class ProductController {
			
	@Autowired	
	ProductDao pdao;
	
	@Autowired
	CatDao cdao;
	
	String page="username";
	
	public ProductController()
	{
		
	}
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView home(ModelAndView  mv,HttpServletRequest request)
	{
		page=new UserController().getCookies(request);
		
		
		mv.setViewName("product/index");
		mv.addObject("username", page);
		mv=UserController.login_check(mv);
		mv.addObject("login", "logout");
	return mv;	
	}
		
	@GetMapping("/addproduct")
	public ModelAndView Addproduct(ModelAndView mv)
	{
		mv.addObject("Product",new Product());
		mv.setViewName("/Product/productfrm");
		mv.addObject("Category", cdao.GetAll());
		mv.addObject("username", page);
		mv=UserController.login_check(mv);
		mv.addObject("login", "logout");
		return mv;
		
	}
	@PostMapping("/saveproduct")
	public ModelAndView saveProduct(ModelAndView mv,HttpServletRequest request,@ModelAttribute Product prod)
	{
		Long a= Long.parseLong(request.getParameter("category"));
		Category cat=cdao.getRecordById(a).get();
		
		prod.setCat(cat);
		pdao.insertRecord(prod);
		mv.setViewName("redirect:/showproduct");
		mv.addObject("username", page);
		mv=UserController.login_check(mv);
		mv.addObject("login", "logout");
		return mv;
		
	}
	@GetMapping("/displayProduct")
	public ModelAndView showProduct(ModelAndView mv)
	{
		mv.addObject("Product",pdao.getAllRecord());
		mv.setViewName("/Product/productshowfrm");
		mv.addObject("username", page);
		mv=UserController.login_check(mv);
		mv.addObject("login", "logout");
		return mv;
		
	}
	
	
		
	}


