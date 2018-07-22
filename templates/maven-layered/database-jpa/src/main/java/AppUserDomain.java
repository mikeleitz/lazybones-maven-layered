package ${base_package}.dao.domain;

import ${base_package}.model.AppUser;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper=true)
@Table(name="app_user")
@Entity
public class AppUserDomain {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private String content;

    public AppUser toModel() {
        return new AppUser(this.getId(), this.getContent());
    }
}