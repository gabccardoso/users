package com.hackaton.user.infrastructure.controllers.dto;

public record LoginDTO (String identity, String password, String email) {
}
