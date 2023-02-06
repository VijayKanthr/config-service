package in.jsw.config.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "USER_MASTER")
@Entity
public class User implements Serializable {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_TYPE")
    private String userType;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "OFFICIAL_MAIL_ID")
    private String emailId;

    @Column(name = "USER_MOBILE_NO")
    private String mobileNumber;


  /*  private Set<Modules> modules = new HashSet<>();
    public void addModule(Modules module) {
        this.modules.add(module);
    }*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MODULE_ID")
    private Modules modules;


    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_MODULES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID")
    )

    private Set<Module> modules;

    public void addModule(Module module) {
        this.modules.add(module);
    }*/

    /*@Getter
    @Setter
    public class ModuleData {
        private boolean sms;
        private boolean hsm;
        private boolean crm;
    }
    private List<ModuleData> modules;
    public void addModule(ModuleData module) {
        this.modules.add(module);
    }*/




}
