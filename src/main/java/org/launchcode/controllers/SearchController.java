package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        ArrayList<HashMap<String, String>> allJobs = JobData.findAll();
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute( "jobs", allJobs);
        model.addAttribute("searchType", "all");
        return "search";
    }
    @RequestMapping(value = "results", method=RequestMethod.GET)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();
        if(searchTerm.equals("")) {
            jobs = JobData.findAll();
        } else {
            if(searchType.equals("all")) {
                jobs = JobData.findByValue(searchTerm);
            } else{
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            }
        }
        model.addAttribute( "jobs", jobs);
        model.addAttribute( "columns", ListController.columnChoices);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchType", searchType);
        return "search";
    }
    // TODO #1 - Create handler to process search request and display results

}