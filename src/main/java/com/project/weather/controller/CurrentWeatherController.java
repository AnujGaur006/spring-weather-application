package com.project.weather.controller;

import com.project.weather.entity.CurrentWeather;
import com.project.weather.service.LiveWeatherService;
import com.project.weather.service.StubWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
//@RequestMapping("/api")
public class CurrentWeatherController {

    private final StubWeatherService stubWeatherService;
    private final LiveWeatherService liveWeatherService;

    public CurrentWeatherController(StubWeatherService stubWeatherService, LiveWeatherService liveWeatherService) {
        this.stubWeatherService = stubWeatherService;
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/weather")
    public String showWeatherForm(Model model){
        model.addAttribute("city", ""); // Initialize with empty values
        model.addAttribute("country", ""); // Initialize with empty values
        return "weather-form";
    }

    @GetMapping("/weather/details")
    public String getCurrentWeather(@RequestParam(name="city", required = true)  String city,
                                    @RequestParam(name="country", required = true) String country, Model model) {
        model.addAttribute("city",city);
        model.addAttribute("country",country);
        if (shouldUseLiveService()) {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(city, country));
        } else {
            model.addAttribute("currentWeather", stubWeatherService.getCurrentWeather(city, country));
        }
        return "weather-details";
    }

    @GetMapping("/weather/default-details")
    public String getCurrentWeather(Model model) {
        if (shouldUseLiveService()) {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("delhi", "india"));
        } else {
            model.addAttribute("currentWeather", stubWeatherService.getCurrentWeather("delhi", "india"));
        }
        return "weather-details";
    }

    private boolean shouldUseLiveService() {
        // Implement your logic to decide whether to use liveWeatherService or stubWeatherService
        // Return true to use liveWeatherService and false to use stubWeatherService.
        return true;
    }
}
