package com.example.study_sideproject.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


// http://localhost:8080/swagger-ui/index.html

@OpenAPIDefinition(
		info = @Info(
				title = "Side-Project API",
				description = "사이드프로젝트 API 명세서",
				version = "v1",
				contact = @Contact(
						name = "beginners",
						email = "sample@email.co.kr"
				)
		)
)

@Configuration
public class SwaggerConfig {

//GroupedOpenApi 설정을 하면 그룹 설정된 api 들만 문서에 노출
//    @Bean
//    public GroupedOpenApi sampleGroupOpenApi() {
//        String[] paths = {"/member/**"};
//
//        return GroupedOpenApi.builder().group("샘플 멤버 API").pathsToMatch(paths)
//            .build();
//    }

}

