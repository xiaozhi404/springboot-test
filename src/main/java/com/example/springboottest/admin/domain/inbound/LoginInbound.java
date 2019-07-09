package com.example.springboottest.admin.domain.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInbound {

    private String userName;

    private String password;
}
