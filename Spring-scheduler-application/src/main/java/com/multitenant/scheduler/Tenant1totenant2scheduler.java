package com.multitenant.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.multitenant.feign.FeignServiceUtil;
import com.multitenant.model.City;

@Component
public class Tenant1totenant2scheduler {

	@Autowired
	FeignServiceUtil feignServiceUtil;

	@Scheduled(fixedRate = 600000)
	public void executeSyncProcess() {
		System.out.println("scheduler called");

		List<City> cityList = feignServiceUtil.getAll("tenant1");
		System.out.println("City size "+cityList.size());
		if (cityList != null && cityList.size() > 0) {
			
			cityList.stream().forEach(city -> {
				System.out.println("City size "+city.getName());
				feignServiceUtil.save(city,"tenant2");
			});
		}

	}
}
