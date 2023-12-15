package vttp.ssf.assessment.eventmanagement.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.User;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    DatabaseService dbSvc;

    // TODO: Task 6
	@GetMapping("/register/{eventId}")
	public String register(@PathVariable("eventId") String eventId, Model model){

		User user = new User();
		Event event = dbSvc.getEvent(Integer.valueOf(eventId)-1);
		String eventName = event.getEventName();
        Long eventDate = event.getEventDate();

		model.addAttribute("eventName", eventName);
        model.addAttribute("eventDate", eventDate);
		model.addAttribute("user", user);

		return "eventregister";
	}
    // TODO: Task 7
    @PostMapping("/register")
    public ModelAndView processRegistration(@Valid @ModelAttribute("user") User user, BindingResult result){
        ModelAndView mav = new ModelAndView();
        
        if(result.hasErrors()){
            mav.setViewName("eventregister");
            return mav;
        }

        Integer age = LocalDate.now().getYear() - user.getDob().getYear();
        if(age < 21){
            mav.setViewName("errorregistration");
            mav.addObject("age", age);
            return mav;
        }

        // int participant = event.getParticipants();
        // int eventSize = event.getEventSize();
        // if(participant > 0){
        //     mav.setViewName("errorregistration");
        //     mav.addObject("limitMessage", "Your request for tickets exceeded the event size");
        //     return mav;
        // }

        mav.setViewName("successregistration");
        return mav;
    }
}
