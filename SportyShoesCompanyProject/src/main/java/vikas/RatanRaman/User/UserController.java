package vikas.RatanRaman.User;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	@Autowired
	UserDao udao;
	static Boolean status=false;
	Logger log=Logger.getAnonymousLogger();
	
	
	@GetMapping("/")
	public ModelAndView getHome(ModelAndView mv,HttpServletRequest request)
	{
		getCookies(request);
		mv.setViewName("/dashboard/index");
		mv.addObject("login", "logout");
		mv=login_check(mv);
		return mv;
	}
	@GetMapping("/showfrm")
	public ModelAndView signup(Model model,ModelAndView mv) {
		model.addAttribute("UserEntity", new UserEntity());
	mv.setViewName("/User/SignUp");
	mv=login_check(mv);
	return mv;
	}

	@PostMapping("/SaveUser")
	public String saveUser(@ModelAttribute UserEntity user) {
		udao.insert(user);

		return "redirect:/showuser";
	}

	
	 @GetMapping("/showuser")
	public ModelAndView showData(ModelAndView mv)
	{ 
		
		 List<UserEntity> user = udao.GetAllRecord();
		 mv.addObject("UserEntity", user);
		 mv.setViewName("/User/showUserPage");
		 mv=login_check(mv);
		 return mv;
	}
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mv)
	{
		mv.addObject("message", "Use Correct Credintials");
		mv.addObject("selector","correct");
		mv.addObject("login","login");
		mv.setViewName("/User/login");
		return mv;
		
	}
	@PostMapping("/doLogin")
	public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response,ModelAndView mv)
	{
		String username=request.getParameter("userName");
		String userpassword=request.getParameter("userPassword");
		UserEntity user=udao.login(username, userpassword);
		if(user!=null)
		{
			Cookie ck=new Cookie("username", username);
			ck.setMaxAge(1*60*60);
			ck.setHttpOnly(true);
			ck.setSecure(true);
			ck.setPath("/");
			response.addCookie(ck);
			UserController.status=true;
			mv.setViewName("redirect:/");
		}
		else
		{
			mv.addObject("message", "Wrong Credintials");
			mv.addObject("selector", "wrong");
			mv.setViewName("/User/login");
		}
		return mv;
		
	}
	@GetMapping("/logout")
	public ModelAndView logout(ModelAndView mv,HttpServletResponse response)
	{
		Cookie ck=new Cookie("username","");
		ck.setMaxAge(0);
		ck.setHttpOnly(true);
		ck.setSecure(true);
		ck.setPath("/");
		UserController.status=false;
		
		response.addCookie(ck);
		mv.setViewName("redirect:/");
				
		return mv;
	}
	@GetMapping("/Credentails")
	public ModelAndView checkLogin(@CookieValue(value="username",defaultValue="nothing")String username,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		String page=request.getParameter("page");
		if(username.equals("nothing"))
		{
			mv.setViewName("redirect:/login");
		}
		else
		{	mv.addObject("username", "Admin");
			mv.setViewName(page);
			log.info(" dashboard info send");
			
		}
			
		return mv;
		
			
		
	}
	
	public String getCookies(HttpServletRequest request)
	{
		String page="no";
		if(request.getCookies()==null)
		{
			
		return page;
		}
	
		for (Cookie ck:request.getCookies())
		{
			if(ck.getName().equals("username"))
			{
				page=ck.getValue();
				UserController.status=true;
				break;
			}
		}
		return page;
		
	
	}
	public static  ModelAndView login_check(ModelAndView mv)
	{
		
		if(!UserController.status)
		{
		mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@GetMapping("/passwordChange")
	public ModelAndView changePassword(ModelAndView mv,HttpServletRequest request)
	{
		mv.addObject("username", getCookies(request));
		mv.setViewName("User/changepassword");
		mv=login_check(mv);
		
	return mv;	
	}
	@PostMapping("/doChange")
	public ModelAndView dochange(ModelAndView mv,HttpServletRequest request,HttpServletResponse response)
	{
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		UserEntity user=udao.login(userName, userPassword);
		String message="Password Change succesfully";
		if(user!=null)
		{
			user.setUserPassword(request.getParameter("newPassword"));
			udao.insert(user);
			
		}
		else
		{
			message="Please Enter the Correct Password and try";
			
		}
		mv.setViewName("User/changepassword");
		mv.addObject("message", message);
		return mv;
	}
	
	
			
}
