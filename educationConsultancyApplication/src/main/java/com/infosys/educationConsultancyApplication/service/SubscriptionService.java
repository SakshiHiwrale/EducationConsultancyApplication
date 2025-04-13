package com.infosys.educationConsultancyApplication.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.educationConsultancyApplication.bean.CourseSubscription;
import com.infosys.educationConsultancyApplication.dao.CourseSubscriptionRepository;

@Service
public class SubscriptionService {

    @Autowired
    private CourseSubscriptionRepository courseSubscriptionRepository;

    public CourseSubscription createSubscription(CourseSubscription courseSubscription) {
      
        courseSubscription.setSubscriptionId(generateSubscriptionId());
     
        String currentDate = LocalDate.now().toString();
        courseSubscription.setSubscriptionDate(currentDate);
        
        String calculatedEndDate = generateEndDate(currentDate);
        courseSubscription.setEndDate(calculatedEndDate);
        
        return courseSubscriptionRepository.save(courseSubscription); // Save to repository
    }

    public List<CourseSubscription> getAllSubscriptions() {
        return courseSubscriptionRepository.findAll();
    }

    private String generateSubscriptionId() {
        String maxId = courseSubscriptionRepository.getMaxId();
        if (maxId == null) {
            return "SC1001"; 
        }
        int numericPart = Integer.parseInt(maxId.substring(2)); 
        numericPart++;
        return "SC" + numericPart; 
    }

    public String generateEndDate(String subscriptionDate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate subDate = LocalDate.parse(subscriptionDate, dateFormat); 
        LocalDate endDate = subDate.plusMonths(3);
        return endDate.toString();
    }
}
