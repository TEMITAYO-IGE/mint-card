package com.example.demo.controller;

import com.example.demo.dto.MintResponse;
import com.example.demo.model.Cards;
import com.example.demo.service.dao.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Osagie Erhabor on 25/02/2020.
 */
@RestController
@RequestMapping("/card-scheme/")
public class cardsController {
    @Autowired
    KafkaTemplate<String, Cards> kafkaTemplate;
    private final static String TOPIC="com.ng.vela.even.card_verified";

    private final CardService cardService;

    public cardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("verify/{cardId}")
    public ResponseEntity findCardsByCardId(@PathVariable Long cardId){
        Cards cards = cardService.findCardsByCardId(cardId);
        if (cards==null){
            return new ResponseEntity<>("Card not found", HttpStatus.OK);
        }
        kafkaTemplate.send(TOPIC,cards);
        return new ResponseEntity<>(new MintResponse(true, cards), HttpStatus.OK);
    }


    @GetMapping("stats")
    public String getCardStats(@RequestParam(value = "start") String start, @RequestParam(value = "limit") String limit ){
        return  start + " " + limit;
    }
}
