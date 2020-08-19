package com.sfac.javaSpringBoot.modules.test.conntroller;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityCountroller {
    @Autowired
    private CityService cityService;
/*127.0.0.1/api/cities/522*/
    @GetMapping("/cities/{countryId}")
    public List<City> getcitysByCountryId(@PathVariable int countryId){
        return cityService.getCitysByCountryId(countryId);
    }

    /*
    * 127.0.0.1/cities/255--put
            * {"currentPage":"1","pageSize":"5"}
    * */
    @PostMapping(value = "/cities/{coutryId}",consumes = "application/json")
    public PageInfo<City> getcitiesBySearchVo(@PathVariable int coutryId,@RequestBody SearchVo searchVo){
        return cityService.getcitiesBySearchvo(coutryId,searchVo);
    }

    /*
    *  127.0.0.1/api/cities---put
    * {"currentPage":"1","pageSize":"5","keyWord":"Sh","orderBy":"city_name","sort":"desc"}
    * */
    @PostMapping(value = "/cities" ,consumes = "application/json")
    public PageInfo<City> getcitiesBySearchvo(@RequestBody SearchVo searchVo){
        return  cityService.getCitiesBySearchVo(searchVo);
    }

    /*
    *127.0.0.1/api/city---post
    *{"cityName":"test1","localCityName":"freedom","countryId":522}
    * */
    @PostMapping(value = "/city",consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city){
        return cityService.insert(city);
    }

    /*
    * 127.0.0.1/api/updatecity-----put
    * "cityId"="1980","cityName"="java"
    * */
    @PutMapping(value = "/city",consumes ="application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city){
        return cityService.updateCity(city);
    }

    /*
    * 127.0.0.1/api/city/2264----\
    * */
    @DeleteMapping(value = "/city/{cityId}")
    public Result<City> delectCity(@PathVariable int cityId){
        return cityService.delectCity(cityId);
    }
}
