package ${base_package}.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class HelloWorld {
    @Getter @Setter private Long id;
    @Getter @Setter private String content;
}