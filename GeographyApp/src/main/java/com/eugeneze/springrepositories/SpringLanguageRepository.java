package com.eugeneze.springrepositories;

import com.eugeneze.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringLanguageRepository extends JpaRepository<Language, Integer> {

    Optional<Language> findLanguageByName(String languageName);
}
