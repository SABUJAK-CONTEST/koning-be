package com.sabujak.contest.domain.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

  @GetMapping("/login/social-info")
  public String socialInfo() {
    return "socialInfo";
  }

}
