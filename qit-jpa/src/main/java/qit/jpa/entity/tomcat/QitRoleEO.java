package qit.jpa.entity.tomcat;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.annotations.Multitenant;

@Entity
@Multitenant
@Table (
		name = "ROLES",
		uniqueConstraints = @UniqueConstraint(columnNames={"TENANT_ID", "ROLE"})
		)
@NamedQueries ({
	@NamedQuery (name = "findAllRoles", query = "SELECT m  FROM RoleEO m ORDER BY m.role ASC")
})
public class QitRoleEO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name="RoleIdGen", table="ROLEIDGEN")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="RoleIdGen")
	@Column (name = "ID")
	private Long id;
	
	@Column (name = "ROLE", nullable=false, length=256)
	private String role;
	
	@Column (name = "DESC", nullable=true, length=512)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
