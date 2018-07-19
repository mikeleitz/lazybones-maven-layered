package ${base_package}.domain;

<% if (useLombok) { %>import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString(callSuper=true)<% } %>
public class HelloWorld {
    <% if (useLombok) { %>@Getter <% } %>private final long id;
    <% if (useLombok) { %>@Getter <% } %>private final String content;
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