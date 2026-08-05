package com.ctrlaltkeeb.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KeyboardGuideController {

  @GetMapping("/keyboard-guide")
  public String keyboardGuide() {
    return "keyboard-guide";
  }
}