package ${base_package}.dao.domain;

import ${base_package}.model.HelloWorld;
        <% if (useLombok) { %>import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper=true)<% } %>
public class HelloWorldDomain {
    <% if (useLombok) { %>@Getter <% } %>private Long id;
    <% if (useLombok) { %>@Getter <% } %>private String content;

    public HelloWorld toModel() {
        return new HelloWorld(this.getId(), this.getContent());
    }

<% if (!useLombok) { %>
    public HelloWorld(long id, String content) {
            this.id = id;
            this.content = content;
        }

        public long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }<% } %>
}