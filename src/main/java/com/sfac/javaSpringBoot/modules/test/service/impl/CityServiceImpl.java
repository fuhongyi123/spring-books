package com.sfac.javaSpringBoot.modules.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.aspect.ServiceAnnotation;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.dao.CityDao;
import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;


    @Override
    @ServiceAnnotation(value = "bbb")
    public List<City> getCitysByCountryId(int countryId) {
        // return cityDao.getCitiesByCountryId(countryId);
        return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getcitiesBySearchvo(int countryId, SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }



    @Override
    @Transactional  //事务注解
    public Result<City> insert(City city) {
        city.setDateCreated(new Date());
        cityDao.insert(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"Insert success.",city);
    }

    @Override
    @Transactional
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"update success.",city);
    }

    @Override
    public Result<City> delectCity(int cityId) {
            cityDao.delectCity(cityId);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"delect success");
    }


}