package in.jsw.config.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "MODULE_MASTER")
@Entity
public class Modules implements Serializable {

    @Id
    @Column(name = "MODULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean SMS;

    @Column
    private boolean HSM;

    @Column
    private boolean CRM;



}
