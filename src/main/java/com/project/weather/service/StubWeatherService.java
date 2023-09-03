package com.project.weather.service;

import com.project.weather.entity.CurrentWeather;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StubWeatherService {
    public CurrentWeather getCurrentWeather(String city) {
        return new CurrentWeather("mock", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN,"test");
    }
}
