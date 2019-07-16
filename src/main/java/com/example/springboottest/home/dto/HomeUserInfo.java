package com.example.springboottest.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeUserInfo implements Serializable {

    private Integer id;

    private List<String> roles;

}
