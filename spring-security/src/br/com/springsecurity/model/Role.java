package br.com.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
 
import org.springframework.security.core.GrantedAuthority;
 
@Entity
@Table(name="roles")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.id = :roleId"),
    @NamedQuery(name = "Role.findByRoleDescription", query = "SELECT r FROM Role r WHERE r.description = :roleDescription")})
public class Role implements GrantedAuthority{
    private static final long serialVersionUID = -3968396919486158590L;
 
    private Integer id;
    private String description;
 
    @Id
    @GeneratedValue
    @Column(name="role_id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="role_description", unique=true)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    @Transient
    public String getAuthority() {
        return description;
    }
}