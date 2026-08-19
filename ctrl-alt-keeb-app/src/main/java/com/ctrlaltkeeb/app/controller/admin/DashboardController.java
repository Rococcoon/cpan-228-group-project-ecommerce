package com.ctrlaltkeeb.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Controller
public class DashboardController {

  private static final String INVENTORY_API_URL = "http://localhost:8081/api/inventory";

  @GetMapping("/admin/dashboard")
  public String dashboard(Model model) {

    RestTemplate restTemplate = new RestTemplate();

    try {
      Map[] inventoryItems = restTemplate.getForObject(INVENTORY_API_URL, Map[].class);

      if (inventoryItems != null) {
        model.addAttribute("inventoryItems", inventoryItems);
      } else {
        model.addAttribute("inventoryItems", Collections.emptyList());
      }

      model.addAttribute("inventoryServiceAvailable", true);

    } catch (RestClientException e) {

      model.addAttribute("inventoryItems", Collections.emptyList());
      model.addAttribute("inventoryServiceAvailable", false);
    }

    return "admin/dashboard";
  }
}