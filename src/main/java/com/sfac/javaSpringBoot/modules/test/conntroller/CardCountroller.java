package com.sfac.javaSpringBoot.modules.test.conntroller;

import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.test.entity.Card;
import com.sfac.javaSpringBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CardCountroller {

    @Autowired
   private CardService cardService;

    /*
    * 127.0.0.1/api/card
    * {}
    * */
    @RequestMapping(value = "/card",consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card){
      return   cardService.insertCard(card);
    }
}
