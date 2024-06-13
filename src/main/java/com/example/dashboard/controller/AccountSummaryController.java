package com.example.dashboard.controller;


import com.example.dashboard.model.AccountSummary;
import com.example.dashboard.service.AccountSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/account-summary")
public class AccountSummaryController {

    @Autowired
    private AccountSummaryService service;

    @GetMapping("/{id}")
    public Mono<String> getAccountSummary(@PathVariable String id, Model model) {
        return service.getAccountSummary(id)
                .map(accountSummary -> {
                    model.addAttribute("accountSummary", accountSummary);
                    return "accountSummary";
                })
                .switchIfEmpty(Mono.just("errorPage")); // Return an error page if no account is found
    }

    @PostMapping
    public Mono<ResponseEntity<AccountSummary>> createAccountSummary(@RequestBody AccountSummary accountSummary) {
        return service.createAccountSummary(accountSummary)
                .map(savedAccountSummary -> ResponseEntity.ok(savedAccountSummary));
    }
}