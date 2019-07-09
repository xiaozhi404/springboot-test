package com.example.springboottest.common.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminTokenDTO {
    private Integer sysUserId;
    private List<String> authorities;
}
