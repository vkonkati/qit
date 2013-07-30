package qit.jpa.entity.tomcat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.annotations.Multitenant;

@Entity
@Multitenant
@Table (
		name = "SUBJECTS",
		uniqueConstraints = @UniqueConstraint(columnNames={"TENANT_ID", "PRINCIPAL"})
		)
@NamedQueries ({
	@NamedQuery (name = "findAllSubjects", query = "SELECT m  FROM SubjectEO m ORDER BY m.principal ASC")
})
public class QitSubjectEO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name="SubjectIdGen", table="SUBJECTIDGEN")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="SubjectIdGen")
	@Column (name = "ID")
	private Long id;
	
	@Column (name = "PRINCIPAL", nullable=false, length=256)
	private String principal;
	
	@Column (name = "CREDENTIAL", nullable=false, length=256)
	private String credential;
	
	@Column (name="CREATEDTS", nullable=false)
	private Timestamp createdTimestamp;
	
	@Column (name="MODIFIEDTS", nullable=false)
	private Timestamp modifiedTimestamp;
	
	@Column (name = "EMAIL", nullable=true, length=256)
	private String email;
	
	@Column (name = "DESC", nullable=true, length=512)
	private String description;
	
	@OneToMany (mappedBy = "subject", orphanRemoval=true, cascade={CascadeType.ALL})
	private Set<QitPrivilegeEO> privileges;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Timestamp getModifiedTimestamp() {
		return modifiedTimestamp;
	}

	public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<QitPrivilegeEO> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<QitPrivilegeEO> privileges) {
		this.privileges = privileges;
	}
}
