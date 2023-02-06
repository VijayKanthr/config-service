package in.jsw.config.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "SCREEN_MASTER")
@Entity
public class Screen implements Serializable {
    @Id
    @Column(name = "SCREEN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SCREEN_NAME")
    private String screenName;

    @Column(name = "MODULE_NAME")
    private String moduleName;

    @Column(name = "SCREEN_DESC")
    private String screenDescription;
}
