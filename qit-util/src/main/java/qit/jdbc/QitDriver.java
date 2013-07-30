package qit.jdbc;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QitDriver implements Driver {
	
	private Driver driver;

	public QitDriver(Driver driver) {
		super();
		this.driver = driver;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		return driver.connect(url, info);
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return driver.acceptsURL(url);
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		return driver.getPropertyInfo(url, info);
	}

	@Override
	public int getMajorVersion() {
		return driver.getMajorVersion();
	}

	@Override
	public int getMinorVersion() {
		return driver.getMinorVersion();
	}

	@Override
	public boolean jdbcCompliant() {
		return driver.jdbcCompliant();
	}

	@Override
	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return driver.getParentLogger();
	}
	
	public static void register(String driver, List<String> jars) throws Exception {
		Logger log = LoggerFactory.getLogger(QitDriver.class);
		if(driver == null || driver.isEmpty()) {
			log.warn("JDBC driver name is null or empty, failed to register driver");
			throw new IllegalArgumentException("JDBC driver name cannot be null/empty");
		}
		if(jars == null || jars.isEmpty()) {
			log.warn("JDBC driver jar list is null or empty, failed to register driver");
			throw new IllegalArgumentException("JDBC jar list cannot be null/empty");
		}
		log.info("trying to register JDBC driver {}", driver);
		List<URL> urls = new ArrayList<URL>();
		boolean missing = false;
		Iterator<String> iter = jars.iterator();
		while(iter.hasNext()) {
			String jar = iter.next();
			log.info("JDBC jar location {}", jar);
			File file = new File(jar);
			if(!file.isFile() || !file.canRead()) {
				missing = true;
				log.warn("JDBC driver jar {}, is not a file or cannot read", jar);
				break;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("jar:");
			sb.append(file.toURI());
			sb.append("!/");
			String fileURL = sb.toString();
			log.info("JDBC class loaded jar URL {}", fileURL);
			URL url = new URL(fileURL);
			urls.add(url);
		}
		if(missing) {
			log.warn("failed to register JDBC driver and JDBC jars");
			return;
		}
		URL[] urlArr = (URL[]) urls.toArray();
		URLClassLoader ucl = new URLClassLoader(urlArr);
		Driver drv = (Driver) Class.forName(driver, true, ucl).newInstance();
		QitDriver jdbcDrv = new QitDriver(drv);
		DriverManager.registerDriver(jdbcDrv);
		log.info("registered JDBC driver {}", driver);
	}

}
