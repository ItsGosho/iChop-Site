package ichop.core.areas.security.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "testt";
    }

}
