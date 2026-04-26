package com.pragma.ms_report.infrastructure.input.rest;

import com.pragma.ms_report.application.dto.BootcampReportDetailResponse;
import com.pragma.ms_report.application.dto.BootcampReportRequest;
import com.pragma.ms_report.application.dto.BootcampReportResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BootcampReportRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/reports/bootcamps",
                    method = RequestMethod.POST,
                    beanClass = BootcampReportRestHandler.class,
                    beanMethod = "save",
                    operation = @Operation(
                            operationId = "saveBootcampReport",
                            summary = "Save bootcamp report",
                            tags = {"Report"},
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BootcampReportRequest.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "201",
                                            content = @Content(
                                                    schema = @Schema(implementation = BootcampReportResponse.class)
                                            )),
                                    @ApiResponse(responseCode = "400", description = "Invalid field")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/reports/bootcamps/{bootcampId}/persons",
                    method = RequestMethod.PATCH,
                    beanClass = BootcampReportRestHandler.class,
                    beanMethod = "incrementPersonCount",
                    operation = @Operation(
                            operationId = "incrementPersonCount",
                            summary = "Increment person count in bootcamp report",
                            tags = {"Report"},
                            parameters = {
                                    @Parameter(name = "bootcampId", in = ParameterIn.PATH,
                                            required = true,
                                            schema = @Schema(type = "integer", format = "int64"))
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200",
                                            content = @Content(
                                                    schema = @Schema(implementation = BootcampReportResponse.class)
                                            )),
                                    @ApiResponse(responseCode = "404",
                                            description = "Bootcamp report not found")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/reports/bootcamps/most-popular",
                    method = RequestMethod.GET,
                    beanClass = BootcampReportRestHandler.class,
                    beanMethod = "findMostPopular",
                    operation = @Operation(
                            operationId = "findMostPopularBootcamp",
                            summary = "Find bootcamp with most enrolled persons",
                            tags = {"Report"},
                            parameters = {},
                            responses = {
                                    @ApiResponse(responseCode = "200",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = BootcampReportDetailResponse.class
                                                    )
                                            )),
                                    @ApiResponse(responseCode = "404",
                                            description = "No reports found")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> bootcampReportRoutes(BootcampReportRestHandler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/reports/bootcamps", handler::save)
                .PATCH("/api/v1/reports/bootcamps/{bootcampId}/persons", handler::incrementPersonCount)
                .GET("/api/v1/reports/bootcamps/most-popular", handler::findMostPopular)
                .build();
    }
}
