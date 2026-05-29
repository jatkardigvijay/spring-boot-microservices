package com.jbd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    private PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    private PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

}
