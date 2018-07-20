package ${base_package}.model;

<% if (useLombok) { %>import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)<% } %>
public class HelloWorld {
    <% if (useLombok) { %>@Getter <% } %>private Long id;
    <% if (useLombok) { %>@Getter <% } %>private String content;
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