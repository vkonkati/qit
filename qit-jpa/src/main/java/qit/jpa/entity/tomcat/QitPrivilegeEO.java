package qit.jpa.entity.tomcat;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.annotations.Multitenant;

@Entity
@Multitenant
@Table (
		name = "PRIVILEGES",
		uniqueConstraints = @UniqueConstraint(columnNames={"TENANT_ID", "PRINCIPAL", "ROLE"})
		)
@NamedQueries ({
	@NamedQuery (name = "findAllPrivileges", query = "SELECT m  FROM PrivilegeEO m ORDER BY m.subject.principal ASC")
})
public class QitPrivilegeEO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name="PrivilegeIdGen", table="PRIVILEGEIDGEN")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="PrivilegeIdGen")
	@Column (name = "ID")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="PRINCIPAL")
	private QitSubjectEO subject;
	
	@OneToOne 
    @JoinColumn(name="ROLE")
	private QitRoleEO role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QitSubjectEO getSubject() {
		return subject;
	}

	public void setSubject(QitSubjectEO subject) {
		this.subject = subject;
	}

	public QitRoleEO getRole() {
		return role;
	}

	public void setRole(QitRoleEO role) {
		this.role = role;
	}
}
