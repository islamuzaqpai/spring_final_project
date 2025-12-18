package com.example.demo.restApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class DirectorDto {
    private Long id;
    private String nameDto;
    private List<Long> moviesIds;
}
