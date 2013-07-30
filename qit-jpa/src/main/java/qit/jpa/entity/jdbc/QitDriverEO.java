package qit.jpa.entity.jdbc;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Multitenant;

@Entity
@Multitenant
@Table (name = "DRIVERS")
@NamedQueries ({
	@NamedQuery (name = "findAllDrivers", query = "SELECT m  FROM DriverEO m ORDER BY m.name ASC")
})
public class QitDriverEO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "ID", length=128)
	private String id;
	
	@Column (name = "NAME", nullable=false, length=256)
	private String name;
	
	@Column (name = "DESC", nullable=true, length=512)
	private String description;
	
	@Column (name = "DRIVER", nullable=false, length=512)
	private String driver;
	
	@OneToMany (mappedBy = "driver", orphanRemoval=true, cascade={CascadeType.ALL})
	private Set<QitJarEO> jars;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Set<QitJarEO> getJars() {
		return jars;
	}

	public void setJars(Set<QitJarEO> jars) {
		this.jars = jars;
	}
}
