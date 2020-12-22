package com.example.demo.service.dao;

import com.example.demo.model.Cards;
import com.example.demo.repository.CardsRepository;
import com.example.demo.util.RestTemplateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.TransactionScoped;

/**
 * Created by Osagie Erhabor on 25/02/2020.
 */
@Service
@TransactionScoped
public class CardService {
    private final CardsRepository cardsRepository;

    @Autowired
    public CardService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public Cards findCardsByCardId(Long cardId){
        Cards cards = cardsRepository.findCardsByCardId(cardId);//Check if card already in DB
        if (cards == null){
            //Call external API
            try {
                String url = "https://lookup.binlist.net/" + cardId;
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("Accept-Version","3");
                RestTemplateUtil restTemplateUtil = new RestTemplateUtil(url, HttpMethod.GET, "", httpHeaders );

                String result = restTemplateUtil.exchange().getBody();
                Gson gson = new Gson();

                JSONObject cardResponse = gson.fromJson(result, JSONObject.class);

                LinkedTreeMap bank = (LinkedTreeMap) cardResponse.get("bank");

                cards = new Cards(cardId, cardResponse.get("scheme").toString(), cardResponse.get("type").toString(), bank.get("name").toString());

                System.out.println(cardResponse);
                cardsRepository.save(cards);
            }catch (HttpClientErrorException e){
                System.err.println(e.toString());
            }catch (Exception e){
                System.err.println(e.toString());
            }
        }
        return cards;
    }

//    public Cards saveCard()
}
