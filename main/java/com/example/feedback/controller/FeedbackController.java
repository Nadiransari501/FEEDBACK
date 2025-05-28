package com.example.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.feedback.model.Feedback;
import com.example.feedback.model.User;
import com.example.feedback.service.FeedbackService;
import com.example.feedback.service.UserService;
import com.example.feedback.service.UserService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String feedbackPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        
        model.addAttribute("feedbacks", feedbackService.getUserFeedbacks(user));
        model.addAttribute("newFeedback", new Feedback());
        return "feedback";
    }
    
    @PostMapping("/add")
    public String addFeedback(@ModelAttribute("newFeedback") Feedback feedback) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        
        feedback.setUser(user);
        feedbackService.saveFeedback(feedback);
        return "redirect:/feedback";
    }
    
    @GetMapping("/edit/{id}")
    public String editFeedback(@PathVariable Long id, Model model) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        model.addAttribute("feedback", feedback);
        return "edit-feedback";
    }
    
    @PostMapping("/update")
    public String updateFeedback(@ModelAttribute("feedback") Feedback feedback) {
        feedbackService.saveFeedback(feedback);
        return "redirect:/feedback";
    }
}
