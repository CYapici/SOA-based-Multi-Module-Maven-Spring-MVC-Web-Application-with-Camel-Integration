package dev.customer.app.controller;

import javax.servlet.http.HttpSession;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dev.customer.entities.Corporation;  
import dev.customer.service.CustomerActionService;
/**
 * the rest controller with camel producer template
 * 
 * @author https://github.com/cagatayes85
 *
 */
@Controller
public class RestController {

	private transient static final Logger logger = LoggerFactory
			.getLogger(RestController.class);

	@Produce(uri = "activemq:queue:client.records")
	private ProducerTemplate template;

	@Autowired
	private CustomerActionService service;

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String handleException(Exception e) {
		return "return error object instead";
	}

	@RequestMapping(value = "/put/{swift}/{iban}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String putAccount(@PathVariable String iban, @PathVariable String swift,
			HttpSession session) throws Exception  
			  {

		Corporation company = service.SaveCorporation(
				session.getId(), iban, swift);

		template.sendBody(company);

		String out = new ObjectMapper().writeValueAsString(company);
		logger.info("out: " + out); 
		return out;
	}

	@RequestMapping(value = "/delete/{corporationID}", method = RequestMethod.DELETE)
	public @ResponseBody String removeAccount(@PathVariable long corporationID,
			HttpSession session) throws Exception     {

		logger.info("removing bank with IBAN: " + corporationID);
		service.DeleteCorporation(session.getId(), new Long(corporationID));
		String out = new ObjectMapper()
				.writeValueAsString("");
		template.sendBody(out);
		return out;
	}
}
