package com.will.udemy.webapi.restfulwebservices;

import com.fasterxml.classmate.TypeResolver;
import com.will.udemy.webapi.restfulwebservices.application.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				/*.alternateTypeRules(
						newRule(typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
								typeResolver.resolve(WildcardType.class)))*/
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						singletonList(new ResponseMessageBuilder()
								.code(500)
								.message("500 message")
								.responseModel(new ModelRef("Error"))
								.build()))
				.securitySchemes(singletonList(apiKey()))
				.securityContexts(singletonList(securityContext()))
				.enableUrlTemplating(true)
				.globalOperationParameters(
						singletonList(new ParameterBuilder()
								.name("someGlobalParameter")
								.description("Description of someGlobalParameter")
								.modelRef(new ModelRef("string"))
								.parameterType("query")
								.required(true)
								.build()))
				.tags(new Tag("restful spring", "This API is a ''evolution'' of 'Master microservices udemy class"))
				.additionalModels(typeResolver.resolve(User.class)
				);
	}

	@Autowired
	private TypeResolver typeResolver;

	private ApiKey apiKey() {
		return new ApiKey("mykey", "api_key", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/anyPath.*"))
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return singletonList(
				new SecurityReference("mykey", authorizationScopes));
	}

	@Bean
	SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
				.clientId("test-app-client-id")
				.clientSecret("test-app-client-secret")
				.realm("test-app-realm")
				.appName("test-app")
				.scopeSeparator(",")
				.additionalQueryStringParams(null)
				.useBasicAuthenticationWithAccessCodeGrant(false)
				.enableCsrfSupport(false)
				.build();
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				.deepLinking(true)
				.displayOperationId(false)
				.defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1)
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE)
				.filter(false)
				.maxDisplayedTags(null)
				.operationsSorter(OperationsSorter.ALPHA)
				.showExtensions(false)
				.showCommonExtensions(false)
				.tagsSorter(TagsSorter.ALPHA)
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null)
				.build();
	}

}
