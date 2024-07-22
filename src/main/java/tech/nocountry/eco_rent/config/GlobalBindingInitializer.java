package tech.nocountry.eco_rent.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import tech.nocountry.eco_rent.util.CustomLocalDateEditor;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalBindingInitializer {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new CustomLocalDateEditor("yyyy-MM-dd"));
    }
}
