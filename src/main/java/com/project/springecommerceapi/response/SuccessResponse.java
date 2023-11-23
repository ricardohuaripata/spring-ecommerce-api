package com.project.springecommerceapi.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SuccessResponse {
    private String type;
    private String message;
}
