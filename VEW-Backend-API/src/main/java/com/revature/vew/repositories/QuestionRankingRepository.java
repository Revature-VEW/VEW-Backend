package com.revature.vew.repositories;

import com.revature.vew.models.QuestionRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRankingRepository extends JpaRepository<QuestionRanking, Integer> {
}
