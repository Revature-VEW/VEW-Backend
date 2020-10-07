package com.revature.vew.repositories;

import com.revature.vew.models.AnswerRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRankingRepository extends JpaRepository<AnswerRanking, Integer> {
}
