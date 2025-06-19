package com.naukri.database_api.repositories;

import com.naukri.database_api.models.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, UUID> {

}
