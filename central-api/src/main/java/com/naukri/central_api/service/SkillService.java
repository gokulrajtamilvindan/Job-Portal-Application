package com.naukri.central_api.service;

import com.naukri.central_api.connector.DataBaseApiConnector;
import com.naukri.central_api.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {

    DataBaseApiConnector dbApiConnector;

    @Autowired
    public SkillService(DataBaseApiConnector dbApiConnector) {
        this.dbApiConnector = dbApiConnector;
    }

    public List<Skill> getAllSkills(List<String> skillNames) {
        List<Skill> skillObjs = new ArrayList<>();

        for (int i = 0; i < skillNames.size(); i++) {
            String skillName = skillNames.get(i);

            Skill skill = this.getSkillByName(skillName);
            skillObjs.add(skill);
        }

        return skillObjs;
    }

    public Skill createSkillByName(String skillName) {
        Skill skill = new Skill();
        skill.setName(skillName);
        return dbApiConnector.callSaveSkillEndpoint(skill);
    }

    public Skill getSkillByName(String skillName) {

        Skill skill = dbApiConnector.callGetSkillByNameEndpoint(skillName);
        if (skill == null) {
            return this.createSkillByName(skillName);
        }
        return skill;

    }

}
