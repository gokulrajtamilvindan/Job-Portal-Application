package com.naukri.central_api.service;

import com.naukri.central_api.connector.DataBaseApiConnector;
import com.naukri.central_api.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    DataBaseApiConnector dbApiConnector;

    @Autowired
    public JobService (DataBaseApiConnector dbApiConnector) {
        this.dbApiConnector = dbApiConnector;
    }

    public Job saveJob(Job job) {
        return dbApiConnector.callSaveJobEndpoint(job);
    }

}
