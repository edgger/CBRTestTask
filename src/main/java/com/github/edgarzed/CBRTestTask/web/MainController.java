package com.github.edgarzed.CBRTestTask.web;

import com.github.edgarzed.CBRTestTask.dto.AbsenceData;
import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;
import com.github.edgarzed.CBRTestTask.service.AbsenceService;
import com.github.edgarzed.CBRTestTask.service.EmployeeService;
import com.github.edgarzed.CBRTestTask.service.PositionService;
import com.github.edgarzed.CBRTestTask.util.AbsenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Enumeration;

@Controller
public class MainController {

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    private static final int PAGE_SIZE = 20;

    @GetMapping("/")
    public String root(@RequestParam(value = "page", required = false, defaultValue = "0") int currentPage,
                       HttpServletRequest request, Model model) {
        Enumeration<String> parameterNames = request.getParameterNames();

        long totalCount = absenceService.getCount();
        int start = getStartAddPageNumbers(currentPage, model, totalCount);

        model.addAttribute("absencesData", AbsenceUtil.convertToDTO(absenceService.getPage(start, PAGE_SIZE)));
        model.addAttribute("employees", employeeService.getFiltered(null, null, null, null));

        return "index";
    }

    @PostMapping("/")
    public String create(@RequestParam(value = "fname") String fname,
                         @RequestParam(value = "mname") String mname,
                         @RequestParam(value = "lname") String lname,
                         @RequestParam(value = "positionId") Short positionId,
                         @RequestParam(value = "date") String date,
                         @RequestParam(value = "minutes") String minutes,
                         @RequestParam(value = "reason") String reason,
                         Model model) {

        fname = fixStringParam(fname);
        mname = fixStringParam(mname);
        lname = fixStringParam(lname);

        model.addAttribute("fname", fname);
        model.addAttribute("mname", mname);
        model.addAttribute("lname", lname);
        model.addAttribute("positionId", positionId);
        model.addAttribute("date", date);
        model.addAttribute("minutes", minutes);
        model.addAttribute("reason", reason);

        LocalDate localDate = null;
        short parseMinutes = 0;
        try {
            localDate = LocalDate.parse(date);
            parseMinutes = Short.parseShort(minutes);
            if (parseMinutes > 1440) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException | DateTimeException e) {
            model.addAttribute("errorMessage", "Incorrect date/minutes data! Try again");
            return getCreate(model);
        }

        Position position = positionService.get(positionId);
        if (position == null) {
            model.addAttribute("errorMessage", "Incorrect position data! Try again");
            return getCreate(model);
        }

        Collection<Employee> employees = employeeService.getFiltered(fname, mname, lname, null);
        Employee employee = null;
        if (employees.size() == 0) {
            employee = employeeService.save(new Employee(fname, mname, lname, position));
        } else {
            for (Employee emplIter : employees) {
                if (emplIter.getPosition().equals(position)) {
                    employee = emplIter;
                    break;
                }
            }
        }
        if (employee == null) {
            model.addAttribute("errorMessage", "Incorrect employee data! Try again");
            return getCreate(model);
        }

        Absence absence = new Absence(localDate, parseMinutes, reason, employee);
        absenceService.save(absence);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {

        Collection<Position> positions = positionService.getAll();
        model.addAttribute("positions", positions);

        return "absenceForm";
    }

    @GetMapping("/search")
    public String getFiltered(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                              @RequestParam(value = "fname", required = false) String fname,
                              @RequestParam(value = "mname", required = false) String mname,
                              @RequestParam(value = "lname", required = false) String lname,
                              @RequestParam(value = "positionId", required = false) Short positionId,
                              @RequestParam(value = "date", required = false) String date,
                              Model model) {

        Position position = positionId == null ? null : positionService.get(positionId);
        LocalDate localDate = null;
        try {
            localDate = (date == null || date.length() == 0) ? null : LocalDate.parse(date);
        } catch (DateTimeException ignored) {
        }

        fname = fixStringParam(fname);
        mname = fixStringParam(mname);
        lname = fixStringParam(lname);

        long totalCount = absenceService.getCountFiltered(fname, mname, lname, position, localDate);
        int start = getStartAddPageNumbers(page, model, totalCount);
        Collection<AbsenceData> absenceData = AbsenceUtil.convertToDTO(absenceService.getPageFiltered(start, PAGE_SIZE, fname, mname, lname, position, localDate));

        Collection<Position> positions = positionService.getAll();
        model.addAttribute("positions", positions);
        model.addAttribute("fname", fname);
        model.addAttribute("mname", mname);
        model.addAttribute("lname", lname);
        model.addAttribute("positionId", positionId);
        model.addAttribute("date", date);
        model.addAttribute("absencesData", absenceData);
        return "search";
    }

    private String fixStringParam(String param) {
        if (param != null) {
            String trimmed = param.trim();
            return trimmed.length() == 0 ? null : trimmed;
        } else {
            return null;
        }
    }

    private static int getStartAddPageNumbers(int currentPage, Model model, long totalCount) {
        int lastPageNumber = (int) (totalCount / PAGE_SIZE);
        lastPageNumber = (totalCount % PAGE_SIZE) == 0 ? lastPageNumber : lastPageNumber + 1;
        int start = currentPage * PAGE_SIZE;
        if (start >= totalCount) {
            start = 0;
            currentPage = 0;
        }
        model.addAttribute("lastPage", lastPageNumber);
        model.addAttribute("currentPage", currentPage);
        return start;
    }
}
