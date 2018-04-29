package com.github.edgarzed.CBRTestTask.web;

import com.github.edgarzed.CBRTestTask.service.AbsenceService;
import com.github.edgarzed.CBRTestTask.service.EmployeeService;
import com.github.edgarzed.CBRTestTask.service.PositionService;
import com.github.edgarzed.CBRTestTask.util.AbsenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class MainController {

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public String root(HttpServletRequest request, Model model) {
        Enumeration<String> parameterNames = request.getParameterNames();
        model.addAttribute("absencesData", AbsenceUtil.convertToDTO(absenceService.getAll()));
        return "index";
    }

    @GetMapping("/search")
    public String getFiltered(@RequestParam(value = "fname", required = false) String fname,
                              @RequestParam(value = "mname", required = false) String mname,
                              @RequestParam(value = "lname", required = false) String lname,
                              @RequestParam(value = "position", required = false) short positionId,
                              @RequestParam(value = "date", required = false) String date,
                              Model model) {
        model.addAttribute("absences", absenceService.getAll());
        return "index";
    }
}
