package name.alexkosarev.tutorial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Framework {

    private int id;

    private String name;

    private String language;

    private String link;

    public Framework(String name, String language, String link) {
        this.name = name;
        this.language = language;
        this.link = link;
    }
}
