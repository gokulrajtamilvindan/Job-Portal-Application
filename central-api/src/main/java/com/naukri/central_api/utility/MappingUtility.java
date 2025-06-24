package com.naukri.central_api.utility;

import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.dto.RecruiterDetailsDto;
import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.model.Company;
import com.naukri.central_api.model.Skill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MappingUtility {

    public AppUser mapJobSeekerDetailsToAppUser(JobSeekerRegistrationDto jobSeekerDto, List<Skill> skills) {

        AppUser appUser = new AppUser();
        appUser.setUserType("JOB_SEEKER");
        appUser.setName(jobSeekerDto.getName());
        appUser.setEmail(jobSeekerDto.getEmail());
        appUser.setPassword(jobSeekerDto.getPassword());
        appUser.setPhoneNumber(jobSeekerDto.getPhoneNumber());
        appUser.setSkillSet(skills);

        return appUser;

    }

    public Company mapCompanyDtoToCompanyModel(CompanyRegistrationDto companyRegistrationDto) {

        Company company = new Company();
        company.setCompanyName(companyRegistrationDto.getCompanyName());
        company.setEmail(companyRegistrationDto.getEmail());
        company.setWebsiteLink(companyRegistrationDto.getWebsiteLink());
        company.setLinkedinLink(companyRegistrationDto.getLinkedinLink());
        company.setCompanySize(companyRegistrationDto.getCompanySize());
        company.setIndustry(companyRegistrationDto.getIndustry());
        return company;

    }

    public AppUser mapCompanyDtoToAdmin(CompanyRegistrationDto companyRegistrationDto, Company company) {

        AppUser admin = new AppUser();
        admin.setCompany(company);
        admin.setName("Admin");
        admin.setEmail(companyRegistrationDto.getEmail());
        admin.setPassword(companyRegistrationDto.getPassword());
        admin.setUserType("ADMIN");
        admin.setPhoneNumber(companyRegistrationDto.getPhoneNumber());
        return admin;

    }

    public AppUser mapRecruiterDtoToAppUser(RecruiterDetailsDto recruiterDetailsDto,
                                            Company company) {

        AppUser user = new AppUser();
        user.setName(recruiterDetailsDto.getName());
        user.setEmail(recruiterDetailsDto.getEmail());
        user.setPhoneNumber(recruiterDetailsDto.getPhoneNumber());
        user.setUserType("RECRUITER");
        user.setPassword("DefaultPass123");
        user.setCompany(company);
        user.setStatus("INACTIVE");
        return user;

    }

}
