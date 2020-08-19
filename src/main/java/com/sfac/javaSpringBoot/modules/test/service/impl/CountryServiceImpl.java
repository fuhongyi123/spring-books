package com.sfac.javaSpringBoot.modules.test.service.impl;

import com.sfac.javaSpringBoot.modules.test.dao.CountryDao;
import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CountryService;
import com.sfac.javaSpringBoot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired CountryDao countryDao;
    @Autowired
    private RedisUtils redisUtils;

   @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountry(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country getCountryByRedis(int countryId) {
        Country country= countryDao.getCountryByCountry(countryId);
           //得到的Country对象保存在redis里  我们就用countryId来标识这个对象  key value
          //%d是一个占位符
        String countryKey=String.format("country%d",countryId);
        redisUtils.set(countryKey, country);
        return (Country) redisUtils.get(countryKey);
    }


}
