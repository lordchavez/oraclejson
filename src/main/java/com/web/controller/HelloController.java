package com.web.controller;

import java.sql.Clob;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.entity.T3;
import com.web.service.ClienteService;
import com.web.service.T3Service;

import javax.annotation.Resource;

@Controller
@RequestMapping
public class HelloController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private T3Service t3Service;

	@RequestMapping( value = "/", method = RequestMethod.GET )
	public ModelAndView hello() {
		return new ModelAndView( "welcome" ).addObject( "content", "Hello World from Spring 4 MVC" );
	}
	
	/*@SuppressWarnings("unlikely-arg-type")
	@RequestMapping( value = "/jpa", method = RequestMethod.GET )
	public ModelAndView getJsonCliente() {
		Map<Long, String> map = clienteService.findAll();
		String string = map.get(123);
		return new ModelAndView( "welcome" ).addObject( "content", string );
		
		Map<Long, Clob> map = clienteService.findByClient();
		Clob clob = map.get(125);
		return new ModelAndView( "welcome" ).addObject( "content", clob.toString() );
	}*/
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping( value = "/id", method = RequestMethod.GET )
	public ModelAndView getById() {
		
		String string = clienteService.findById( 125L );
		return new ModelAndView( "welcome" ).addObject( "content", string );
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
	
	@RequestMapping( value = "/in", method = RequestMethod.GET )
	public ModelAndView insertJson() {
		
		Boolean result = clienteService.insertJson();
		return new ModelAndView( "welcome" ).addObject( "content", result );
	}

	@RequestMapping( value = "/t3", method = RequestMethod.GET )
	public ModelAndView getT3() {
		
		T3 t3 = t3Service.findById( 1 );
		return new ModelAndView( "welcome" ).addObject( "content", t3.getC1().getC2() + " " + t3.getC2() );
	}

	@RequestMapping( value = "/dto", method = RequestMethod.GET )
	public ModelAndView getDto() {

		clienteService.insert();
		return new ModelAndView( "welcome" ).addObject( "content", "Done" );
	}

	@RequestMapping( value = "/f", method = RequestMethod.GET )
	public ModelAndView getFile() {

		return new ModelAndView( "welcome" );
	}
}