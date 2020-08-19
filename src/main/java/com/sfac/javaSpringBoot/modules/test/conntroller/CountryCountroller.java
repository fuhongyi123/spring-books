package com.sfac.javaSpringBoot.modules.test.conntroller;

import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CountryCountroller {

   /* @Autowired
    private CountryService countryService;*/
    /*
    127.0.0.1/api/country/522
     */


  /*  @GetMapping("/country/{countryId}")
    public Country getCountryByCountry(@PathVariable int countryId){
        return countryService.getCountryByCountryId(countryId);
    }

     *//*127.0.0.1/api/country?countryName=china*//*
    @GetMapping("/country")
     public Country getCountryByCountryName(@RequestParam String countryName){
        return countryService.getCountryByCountryName(countryName);
     }*/






    @Autowired
    private CountryService countryService;
    /*
    * 127.0.0.1/api/countryId/522
    * */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId){
        return countryService.getCountryByCountryId(countryId);
    }

    /*
    * 127.0.0.1/country?countryName=china
    * */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName){
        return countryService.getCountryByCountryName(countryName);
    }
        /*
        *127.0.0.1/api/redis/countryRdis/522-----get
        * */
    @GetMapping("/redis/countryRdis/{countryId}")
    public Country getCountryByRedis(@PathVariable int countryId){
        return countryService.getCountryByRedis(countryId);
    }




















}
