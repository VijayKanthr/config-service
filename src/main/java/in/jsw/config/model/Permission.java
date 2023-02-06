package in.jsw.config.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "PERMISSION_MASTER")
@Entity
public class Permission implements Serializable {

    @Id
    @Column(name = "PERMISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PERMISSION_NAME")
    private String permissionName;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERMISSION_SCREEN",
            joinColumns = @JoinColumn(name = "PERMISSION_ID"),
            inverseJoinColumns = @JoinColumn(name = "SCREEN_ID")
    )

    private Set<Screen> screens = new HashSet<>();

    public void addScreen(Screen screen) {
        this.screens.add(screen);
    }


}
