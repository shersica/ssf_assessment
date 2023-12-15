package vttp.ssf.assessment.eventmanagement.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.User;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	DatabaseService dbSvc;

	//TODO: Task 5
	@GetMapping("/listings")
	public String displayEvents(Model model){
		
		List<Event> events = dbSvc.getList();

		model.addAttribute("events", events);

		return "eventlist";
	}

	@GetMapping("/register/{eventId}")
	public String register(){

		return "redirect:/registration/register/{eventId}";
	}




}
