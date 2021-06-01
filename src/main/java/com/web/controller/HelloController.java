package com.web.controller;

import java.sql.Clob;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.service.ClienteService;

@Controller
@RequestMapping
public class HelloController {
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping( value = "/", method = RequestMethod.GET )
	public ModelAndView hello() {
		return new ModelAndView( "welcome" ).addObject( "content", "Hello World from Spring 4 MVC" );
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping( value = "/jpa", method = RequestMethod.GET )
	public ModelAndView getJsonCliente() {
		/*Map<Long, String> map = clienteService.findAll();
		String string = map.get(123);
		return new ModelAndView( "welcome" ).addObject( "content", string );*/
		
		Map<Long, Clob> map = clienteService.findByClient();
		Clob clob = map.get(123);
		return new ModelAndView( "welcome" ).addObject( "content", clob.toString() );
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping( value = "/json", method = RequestMethod.GET )
	public ModelAndView getJson() {
		
		String string = clienteService.findJson();
		return new ModelAndView( "welcome" ).addObject( "content", string );
	}
	
	@RequestMapping( value = "/up", method = RequestMethod.GET )
	public ModelAndView updateJson() {
		
		Boolean result = clienteService.updateJson();
		return new ModelAndView( "welcome" ).addObject( "content", result );
	}

}