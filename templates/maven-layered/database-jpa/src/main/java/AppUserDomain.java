package ${base_package}.dao.domain;

import ${base_package}.model.AppUser;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper=true)
@Table(name="app_user")
@Entity
public class AppUserDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private String content;

    public AppUser toModel() {
        return new AppUser(this.getId(), this.getContent());
    }
}