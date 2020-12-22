package com.example.demo.repository;

import com.example.demo.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Osagie Erhabor on 24/02/2020.
 */
@Repository
public interface CardsRepository extends JpaRepository<Cards,Long>{
    Cards findCardsByCardId(Long cardId);
}
