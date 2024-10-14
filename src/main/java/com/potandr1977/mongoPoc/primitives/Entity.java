package com.potandr1977.mongoPoc.primitives;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter(AccessLevel.PROTECTED)
public class Entity {

    private String Id;

    private Long timeStamp;
}
