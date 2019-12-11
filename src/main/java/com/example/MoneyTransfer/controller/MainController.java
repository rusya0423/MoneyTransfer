package com.example.MoneyTransfer.controller;


import com.example.MoneyTransfer.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API для перевода")
@RestController
@RequestMapping("/api/v1/transfer")
@AllArgsConstructor
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private static final String DEFAULT_PRODUCE_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

       private TransactionService transactionService;

    @ApiOperation(value = "Отправка перевода", notes = "Отправка перевода")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    @PostMapping(value = "", produces = DEFAULT_PRODUCE_MEDIA_TYPE)
    public ResponseEntity<Void> sendTransfer(
            @ApiParam(value = "from", required = true) @RequestParam(name = "from") String from,
            @ApiParam(value = "to", required = true) @RequestParam(name = "to") String to,
            @ApiParam(value = "sum", required = true) @RequestParam(name = "sum") BigDecimal sum) {
        log.debug("REST request to sendTransfer from ={}, to={}, sum={}", from, to, sum);
        transactionService.sendMoney(from, to, sum);
        return ResponseEntity.ok().build();
    }
}
