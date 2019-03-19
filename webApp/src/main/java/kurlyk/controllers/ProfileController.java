package kurlyk.controllers;

import kurlyk.security.detalis.UserDetailsImpl;
import kurlyk.transfer.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto user = UserDto.from(details.getUser());
        model.addAttribute("user", user);
        return "profile";
    }
}
