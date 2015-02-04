package telephony.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import telephony.web.Environment;
import telephony.web.EnvironmentResolver;
 
@Controller
@RequestMapping("/")
public class BaseController {
	
	public BaseController() { }

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView main() {

		ModelAndView mv = new ModelAndView("dashboard");
		appendEnvironment(mv);

		return mv;
	}

	// deliveries
	@RequestMapping(value="deliveries", method = RequestMethod.GET)
	public ModelAndView deliveries() {

		ModelAndView mv = new ModelAndView("deliveries");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="addDelivery", method = RequestMethod.GET)
	public ModelAndView addDelivery() {

		ModelAndView mv = new ModelAndView("addDelivery");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="editDelivery/{id}", method = RequestMethod.GET)
	public ModelAndView editDelivery(@PathVariable(value="id") Long id) {

		ModelAndView mv = new ModelAndView("editDelivery");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="deliveryDetails/{id}", method = RequestMethod.GET)
	public ModelAndView deliveryDetails(@PathVariable(value="id") Long id) {

		ModelAndView mv = new ModelAndView("deliveryDetails");
		appendEnvironment(mv);

		return mv;
	}

	// products
	@RequestMapping(value="products", method = RequestMethod.GET)
	public ModelAndView products() {

		ModelAndView mv = new ModelAndView("products");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="productDetails/{id}", method = RequestMethod.GET)
	public ModelAndView productDetails(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("productDetails");
		appendEnvironment(mv);

		return mv;
	}

	// sales
	@RequestMapping(value="sales", method = RequestMethod.GET)
	public ModelAndView sales() {

		ModelAndView mv = new ModelAndView("sales");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="addSale", method = RequestMethod.GET)
	public ModelAndView addSale() {

		ModelAndView mv = new ModelAndView("addSale");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="editSale/{id}", method = RequestMethod.GET)
	public ModelAndView editSale(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("editSale");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="saleDetails/{id}", method = RequestMethod.GET)
	public ModelAndView saleDetails(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("saleDetails");
		appendEnvironment(mv);

		return mv;
	}

	// contacts
	@RequestMapping(value="contacts", method = RequestMethod.GET)
	public ModelAndView contacts() {

		ModelAndView mv = new ModelAndView("contacts");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="addContact", method = RequestMethod.GET)
	public ModelAndView addContact() {

		ModelAndView mv = new ModelAndView("adContact");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="editContact/{id}", method = RequestMethod.GET)
	public ModelAndView editContact(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("editContact");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="contactDetails/{id}", method = RequestMethod.GET)
	public ModelAndView contactDetails(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("contactDetails");
		appendEnvironment(mv);

		return mv;
	}

	// stores
	@RequestMapping(value="stores", method = RequestMethod.GET)
	public ModelAndView stores() {

		ModelAndView mv = new ModelAndView("stores");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="addStore", method = RequestMethod.GET)
	public ModelAndView addStore() {

		ModelAndView mv = new ModelAndView("addStore");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="editStore/{id}", method = RequestMethod.GET)
	public ModelAndView editStore(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("editStore");
		appendEnvironment(mv);

		return mv;
	}

	// taxes
	@RequestMapping(value="taxes", method = RequestMethod.GET)
	public ModelAndView taxes() {

		ModelAndView mv = new ModelAndView("taxes");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="addTax", method = RequestMethod.GET)
	public ModelAndView addTax() {

		ModelAndView mv = new ModelAndView("addTax");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="editTax/{id}", method = RequestMethod.GET)
	public ModelAndView editTax(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("editTax");
		appendEnvironment(mv);

		return mv;
	}

	// complaints
	@RequestMapping(value="complaints", method = RequestMethod.GET)
	public ModelAndView complaints() {

		ModelAndView mv = new ModelAndView("complaints");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="addComplaint", method = RequestMethod.GET)
	public ModelAndView addComplaint() {

		ModelAndView mv = new ModelAndView("addComplaint");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="editComplaint/{id}", method = RequestMethod.GET)
	public ModelAndView editComplaint(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("editComplaint");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="complaintDetails/{id}", method = RequestMethod.GET)
	public ModelAndView complaintDetails(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("complaintDetails");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="commentComplaintAnonymously/{id}", method = RequestMethod.GET)
	public ModelAndView commentComplaintAnonymously(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("commentComplaintAnonymously");
		appendEnvironment(mv);

		return mv;
	}

	// users
	@RequestMapping(value="users", method = RequestMethod.GET)
	public ModelAndView users() {

		ModelAndView mv = new ModelAndView("users");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="addUser", method = RequestMethod.GET)
	public ModelAndView addUser() {

		ModelAndView mv = new ModelAndView("addUser");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="editUser/{id}", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("editUser");
		appendEnvironment(mv);

		return mv;
	}

	// misc
	@RequestMapping(value="login", method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView mv = new ModelAndView("login");
		appendEnvironment(mv);

		return mv;
	}

	@RequestMapping(value="logout", method = RequestMethod.GET)
	public ModelAndView logout() {

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
