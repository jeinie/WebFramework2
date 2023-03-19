package kr.ac.hansung.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// lombok 을 이용해 간단하게 getter/setter 생성
@Getter
@Setter
@NoArgsConstructor
public class Offer {

    private int id;
    private String name;
    private String email;
    private String text;

}
