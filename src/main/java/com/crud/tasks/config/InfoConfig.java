package com.crud.tasks.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoConfig implements InfoContributor{

        @Override
        public void contribute(Info.Builder builder) {
             builder.withDetail("app", createAppInfo())
                    .withDetail("company", createCopmanyInfo());
        }


        private static Map<String, Object> createAppInfo(){
            Map<String, Object> result = new HashMap<>();
            Map<String, Object> ownerResult = new HashMap<>();
            Map<String, Object> addresResult = new HashMap<>();
            Map<String, Object> adminResult = new HashMap<>();

            addresResult.put("stret","Super Cool Street");
            addresResult.put("number", "42");

            adminResult.put("email", "john.doe@test.com");
            adminResult.put("address", addresResult);

            ownerResult.put("name", "John");
            ownerResult.put("surname", "Doe");

            result.put("name", "Task Application");
            result.put("description", "Application created on kodilla course");
            result.put("version", "1.0.0");
            result.put("owner", ownerResult);
            result.put("administrator", adminResult);

            return result;
        }

        private static Map<String, Object> createCopmanyInfo() {
            Map<String, Object> result = new HashMap<>();
            result.put("name","TaskCrudInformation");
            result.put("goal", "our gole is make the world a better place");
            result.put("email", "task@crud.com");
            result.put("phone", "134567889");

            return result;
        }

}

