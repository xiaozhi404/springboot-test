package com.example.springboottest.admin.domain.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginOutbound {

    private UserOutbound userOutbound;

    private String token;

}
