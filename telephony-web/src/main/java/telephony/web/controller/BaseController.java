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

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView main(ModelMap model) {

		ModelAndView mv = new ModelAndView("dashboard");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="deliveries", method = RequestMethod.GET)
	public ModelAndView deliveries(ModelMap model) {

		ModelAndView mv = new ModelAndView("deliveries");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="products", method = RequestMethod.GET)
	public ModelAndView products(ModelMap model) {

		ModelAndView mv = new ModelAndView("products");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="sales", method = RequestMethod.GET)
	public ModelAndView sales(ModelMap model) {

		ModelAndView mv = new ModelAndView("sales");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="contacts", method = RequestMethod.GET)
	public ModelAndView contacts(ModelMap model) {

		ModelAndView mv = new ModelAndView("contacts");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="stores", method = RequestMethod.GET)
	public ModelAndView stores(ModelMap model) {

		ModelAndView mv = new ModelAndView("stores");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="taxes", method = RequestMethod.GET)
	public ModelAndView taxes(ModelMap model) {

		ModelAndView mv = new ModelAndView("taxes");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="complaints", method = RequestMethod.GET)
	public ModelAndView complaints(ModelMap model) {

		ModelAndView mv = new ModelAndView("oomplaints");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="users", method = RequestMethod.GET)
	public ModelAndView users(ModelMap model) {

		ModelAndView mv = new ModelAndView("users");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {

		ModelAndView mv = new ModelAndView("login");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model) {

		ModelAndView mv = new ModelAndView("logout");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="dashboard", method = RequestMethod.GET)
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
