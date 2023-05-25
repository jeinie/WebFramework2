package kr.ac.hansung.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// lombok 을 이용해 간단하게 getter/setter 생성
@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue
    @Column(name = "offer_id")
    private int id;

    @Size(min=2, max=100, message = "Name must be between 2 and 100 chars")
    private String name;

    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "The email address can not be empty")
    private String email;

    @Size(min=5, max=100, message = "Text must be between 5 and 100 chars")
    private String text;

    public Offer(String name, String email, String text) {
        this.name = name;
        this.email = email;
        this.text = text;
    }
}
