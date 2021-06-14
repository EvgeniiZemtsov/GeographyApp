package com.eugeneze.springrepositories;

import com.eugeneze.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Repository
public interface SpringLanguageRepository extends JpaRepository<Language, Integer> {

    @Query("SELECT l FROM Language l WHERE l.name = ?1")
    Optional<Language> findLanguageByName(String languageName);
}
