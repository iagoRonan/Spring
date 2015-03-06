package br.com.springsecurity.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name="User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUserPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByUserUsername", query = "SELECT u FROM User u WHERE u.username = :username")})
public class User implements UserDetails{
    private static final long serialVersionUID = -7590317347612436291L;
 
    private Integer id;
    private String username;
    private String password;
    private List<Role> roles;
 
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    @Column(name = "user_username", unique = true)
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    @Column(name = "user_password")
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Role> getRoles() {
        return roles;
    }
 
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
 
    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
 
    @Override
    @Transient
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    @Transient
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    @Transient
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }
}