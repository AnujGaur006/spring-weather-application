package com.project.weather.controller;

import com.project.weather.service.LiveWeatherService;
import com.project.weather.service.StubWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrentWeatherController {

    private final StubWeatherService stubWeatherService;
    private final LiveWeatherService liveWeatherService;

    public CurrentWeatherController(StubWeatherService stubWeatherService, LiveWeatherService liveWeatherService) {
        this.stubWeatherService = stubWeatherService;
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/weather")
    public String showWeatherForm(Model model){
        model.addAttribute("city");
        return "weather-form";
    }

    @GetMapping("/weather/details")
    public String getCurrentWeather(@RequestParam(name="city", required = true)  String city,
                                    Model model) {
//        model.addAttribute("city",city);
        if (shouldUseLiveService()) {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(city));
        } else {
            model.addAttribute("currentWeather", stubWeatherService.getCurrentWeather(city));
        }
        return "weather-details";
    }

    private boolean shouldUseLiveService() {
        // Implement your logic to decide whether to use liveWeatherService or stubWeatherService
        // Return true to use liveWeatherService and false to use stubWeatherService.
        return true;
    }
}
