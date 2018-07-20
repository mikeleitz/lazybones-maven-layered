package ${base_package}.dao.domain;

import ${base_package}.model.HelloWorld;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper=true)
@Entity
public class HelloWorldDomain {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private String content;

    public HelloWorld toModel() {
        return new HelloWorld(this.getId(), this.getContent());
    }
}