package org.jknetl.sleepmetrics.web;

import lombok.extern.slf4j.Slf4j;
import org.jknetl.sleepmetrics.data.Sleep;
import org.jknetl.sleepmetrics.data.User;
import org.jknetl.sleepmetrics.repo.SleepRepository;
import org.jknetl.sleepmetrics.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("/sleep")
@Slf4j
public class SleepController {

    @Autowired
    private SleepRepository sleepRepo;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String showAddSleepForm(Model model) {

        Sleep sleep = new Sleep();
        sleep.setFrom(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(22, 00)));
        sleep.setTill(LocalDateTime.of(LocalDate.now(), LocalTime.of(7,0)));
        sleep.setAwake(Duration.ZERO);
        model.addAttribute("sleep", sleep);
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String showAddSleepForm( @PathVariable("id") Long id, Model model) {

        Optional<Sleep> sleep = sleepRepo.findById(id);

        if (sleep.isPresent()) {
            model.addAttribute("sleep", sleep.get());
            return "add";
        } else {
            //TODO: error handling
            log.warn("Non implemented yet! (edit on non-existing sleep record)");
            return "nonimplemented-error";
        }
    }

    @GetMapping("/list")
    public String listSleepRecords(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("Cannot find user: " + username));

        model.addAttribute("sleeps", sleepRepo.findByUser(user));
        return "list";
    }

    @PostMapping("/add")
    public String processSleepRecord(@ModelAttribute Sleep sleep, Principal principal, Errors errors) {

        String username = principal.getName();
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("Cannot find user: " + username));

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                log.info("Error in sleep record input: " + e);
            }

            return "add";
        }
        sleep.setUser(user);
        sleepRepo.save(sleep);
        return "redirect:/sleep/list";
    }


}
