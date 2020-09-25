package com.revature.vew.repositories;

import com.revature.vew.models.QuestionRanking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRankingRepository extends JpaRepository<QuestionRanking, Integer> {
}
