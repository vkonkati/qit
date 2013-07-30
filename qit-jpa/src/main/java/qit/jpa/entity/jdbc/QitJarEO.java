package qit.jpa.entity.jdbc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Multitenant;

@Entity
@Multitenant
@Table (name = "JARS")
public class QitJarEO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "ID", length=128)
	private String id;
	
	@Column (name = "NAME", nullable=false, length=256)
	private String name;
	
	@ManyToOne
    @JoinColumn(name="DRIVER")
	private QitDriverEO driver;
	
	@Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="jar", columnDefinition="BLOB NOT NULL")
    protected byte[] jar;

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

	public QitDriverEO getDriver() {
		return driver;
	}

	public void setDriver(QitDriverEO driver) {
		this.driver = driver;
	}

	public byte[] getJar() {
		return jar;
	}

	public void setJar(byte[] jar) {
		this.jar = jar;
	}
}
