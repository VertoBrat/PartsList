package ru.photorex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.photorex.entity.Part;
import ru.photorex.service.PartService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/part")
public class MainController {

    @Autowired
    private PartService partService;

    private int pageSize = 10;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/list")
    public String getListOfParts(@RequestParam(name="pageNumber", required=false, defaultValue = "1") int pageNumber, Model model) {
        long totalCustomerCount = partService.getPartCount();
        int totalPages = (int) Math.floor(totalCustomerCount / pageSize);
        if ( (totalCustomerCount % pageSize) > 0) {
            totalPages++;
        }
        List<Part> parts = partService.getPartsByPage(pageNumber);
        model.addAttribute("listOfparts", parts);
        model.addAttribute("totalCustomerCount", totalCustomerCount);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("isPage", "1");

        int count = partService.getCountUseComputers();
        model.addAttribute("count", count);
        model.addAttribute("isCount", "isCount");
        return "list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAddingPart(Model model){
        Part part = new Part();
        model.addAttribute("newPart", part);
        return "form";
    }

    @PostMapping("/addPartToDataBase")
    public String addPartToDataBase(@Valid @ModelAttribute("newPart") Part part, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }

        partService.addPart(part);
        return "redirect:/";
    }

    @GetMapping("/deletePart")
    public String deletePartFromDataBase(@RequestParam("partId") int id) {
        partService.deletePart(id);
        return "redirect:/";
    }

    @GetMapping("/updatePart")
    public String showEditPartForm(@RequestParam("partId") int id, Model model) {
        Part part = partService.getPartFromDataBase(id);
        model.addAttribute("newPart", part);
        return "form";
    }

    @PostMapping("/search")
    public String showSearchedPart(@RequestParam("searchName") String searchName, Model model) {
        List<Part> list = partService.getListOfSearchPart(searchName);
        model.addAttribute("listOfparts", list);
        return "list";
    }

    @GetMapping("/filterPerUse")
    public String getListOfPartsPerUse(@RequestParam("filterParam") String filter, Model model) {
        List<Part> list = partService.getListOfPartPerUse(filter);
        model.addAttribute("listOfparts", list);
        if (filter.equals("use")) {
            int count = partService.getCountUseComputers();
            model.addAttribute("count", count);
            model.addAttribute("isCount", "isCount");
        }
        return "list";
    }
}
