package com.example.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/financial")
public class FinancialController {

    @RequestMapping(value = "/baoxiaoManagement")
    public String reimburseManagement() {
        return "baoxiaoManagement";
    }
    @RequestMapping(value = "/BOSSbaoxiaoManagement")
    public String BOSSreimburseManagement() {
        return "BOSSbaoxiaoManagement";
    }
    @RequestMapping(value = "/bonusManagement")
    public String bonusManagement() {
        return "bonusManagement";
    }
    @RequestMapping(value = "/customerDeal")
    public String comstomerDeal() {
        return "customerDeal";
    }
}
