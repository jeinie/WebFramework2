package kr.ac.hansung.cse.animals;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//@AllArgsConstructor // 모든 인자를 가진 생성자
public class PetOwner {

    @Autowired // wiring by type
    @Qualifier("qf_dog")
    public AnimalType animal;

    /*public PetOwner(AnimalType animal) {
        this.animal = animal;
    }*/

    public void play() {
        animal.sound();
    }
}
