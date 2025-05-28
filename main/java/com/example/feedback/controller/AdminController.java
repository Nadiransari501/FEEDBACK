package com.example.feedback.controller;

 
//import com.feedback.model.Feedback;
//import com.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.feedback.service.FeedbackService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private FeedbackService feedbackService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAllFeedbacks());
        return "admin-dashboard";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/admin/dashboard";
    }
}