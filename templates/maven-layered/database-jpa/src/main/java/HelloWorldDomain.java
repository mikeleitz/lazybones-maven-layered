package ${base_package}.dao.domain;

import ${base_package}.model.HelloWorld;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper=true)
public class HelloWorldDomain {
    @Getter @Setter private Long id;
    @Getter @Setter private String content;

    public HelloWorld toModel() {
        return new HelloWorld(this.getId(), this.getContent());
    }
}