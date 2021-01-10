package by.andrew.demo.entity.parts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Category category;
    private String name;
    @OneToOne
    private Car car;
    private String brand;
    private double price;

}
