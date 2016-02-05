package dev.customer.app.controller;

import javax.servlet.http.HttpSession;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.customer.action.CustomerAction;
import dev.customer.service.CustomerActionService;
/**
 * init the list of banks per user 
 * @author https://github.com/cagatayes85
 *
 */
@Controller
public class HomeController {
	private transient static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private CustomerActionService customerService;

	@Produce(uri = "activemq:queue:client.records")
	private ProducerTemplate template;

	@RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	public ModelAndView homePage(ModelMap model, HttpSession session) {
		
		//data list per id 
		CustomerAction action = customerService.Init(session.getId());
		
		logger.info("actions" + action);
		
		//send initialized data 
		template.sendBody(action);

		model.addAttribute("action", action); 
		return new ModelAndView("home", model);
	}

}