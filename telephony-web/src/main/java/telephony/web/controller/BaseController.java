package telephony.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import telephony.web.Environment;
import telephony.web.EnvironmentResolver;
 
@Controller
@RequestMapping("/")
public class BaseController {
	
	public BaseController() {
		
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		
		ModelAndView mv = new ModelAndView("login");
		appendEnvironment(mv);
		 
		return mv; 
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.GET) 
	public ModelAndView dashboard() {
		
		ModelAndView mv = new ModelAndView("dashboard");
		appendEnvironment(mv);
		
		return mv;
	}
	
	private void appendEnvironment(ModelAndView mv) {
		
		Environment env = EnvironmentResolver.resolve();		
		mv.addObject("environment", env);		
	}		
}
