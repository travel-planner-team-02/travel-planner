package com.laioffer.travelplannerbe.model;

public record ErrorResponse(
        String message,
        String error
) {
}
