package com.vuw17.dto.checkout;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CheckOutRequest {
    private List<Integer> roomIds;
}
