package com.staroot.h2multi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class JsonController {
    private Map<String, Object> jsonData = new HashMap<>();
    @GetMapping("/edit")
    public String getSampleJson(Model model) {
        String jsonData = "{ \"person\": { \"name\": \"John\", \"age\": 30, \"address\": { \"city\": \"New York\", \"zipcode\": \"10001\" } } }";

        model.addAttribute("jsonData", jsonData);
        return "editJson";
    }
    @PostMapping("/edit")
    public String updateJson(@RequestBody Map<String, Object> updatedData) {
        jsonData.putAll(updatedData);
        return "redirect:/edit";
    }
}
