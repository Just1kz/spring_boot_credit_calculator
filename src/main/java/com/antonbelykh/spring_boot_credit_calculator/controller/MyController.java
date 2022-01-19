package com.antonbelykh.spring_boot_credit_calculator.controller;


import com.antonbelykh.spring_boot_credit_calculator.entity.Credit;
import com.antonbelykh.spring_boot_credit_calculator.entity.Payment;
import com.antonbelykh.spring_boot_credit_calculator.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class MyController {

    private final ApplicationContext appContext;

    private final CreditService creditService;

    @Autowired
    public MyController(CreditService creditService, ApplicationContext appContext) {
        this.creditService = creditService;
        this.appContext = appContext;
    }

    @RequestMapping("/")
    public String getAllCredits(Model model) {
        List<Credit> list = creditService.getAll();
        model.addAttribute("credits", list);
    return "credits";
    }

    @RequestMapping("/main")
    public String backToMainPage() {
        return "redirect:/";
    }

    @RequestMapping("/addCredit")
    public String addCredit(Model model) {
        Credit credit = new Credit();
        credit.setStatus("Payments is not calculated");
        model.addAttribute("credit", credit);
        return "add_credit";
    }

    @RequestMapping("/editCredit")
    public String editCredit(@RequestParam("creditId") int creditId, Model model) {
        Credit credit = creditService.getId(creditId);
        model.addAttribute("credit", credit);
        return "add_credit";
    }

    @RequestMapping("/saveCredit")
    public String saveCredit(@Valid @ModelAttribute("credit") Credit credit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_credit";
        }
        credit.setStatus("Payments is not calculated");
        creditService.saveOrUpdate(credit);
        if (credit.getId() != 0) {
            creditService.deleteScheduleAfterUpdateCreditParams(credit.getId());
        }
        return "redirect:/";
    }

    @RequestMapping("/deleteCredit")
    public String deleteCredit(@RequestParam("creditId") int creditId) {
        creditService.deleteById(creditId);
        return "redirect:/";
    }

    @RequestMapping("/showPaymentsOfCredit")
    public String showPaymentsOfCredit(@RequestParam("creditId") int creditId, Model model) {
        Credit credit = creditService.getId(creditId);
        List<Payment> paymentList = credit.getPaymentList();
        model.addAttribute("payments", paymentList);
        return "payments_of_credit";
    }

    @RequestMapping("/calculateSchedulePayments")
    public String calculateSchedulePayments(@RequestParam("creditId") int creditId) {
        creditService.calculateSchedulePayments(creditId);
        Credit credit = creditService.getId(creditId);
        credit.setStatus("Calculate is Done!");
        creditService.saveOrUpdate(credit);
        return "redirect:/";
    }

    @RequestMapping("/beans")
    public String  getBeanList(Model model) {
        model.addAttribute("beans", Arrays.asList(appContext.getBeanDefinitionNames()));
        return "monitoring_beans";
    }
}
