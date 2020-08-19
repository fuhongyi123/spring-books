package com.sfac.javaSpringBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitysByCountryId(int CountryId);
    //分页查询
    PageInfo<City> getcitiesBySearchvo(int countryId , SearchVo searchVo);

    PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);

   //增，删，改全部改成Result对象
    Result<City> insert(City city);

    Result<City> updateCity(City city);

    Result<City> delectCity(int cityId);


}
