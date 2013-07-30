package qit.home;

import java.io.File;

public enum Layout {
	data,
	bin,
	lib,
	etc,
	sql,
	log,
	tmp,
	imdb { public String toString() { return data.toString() + File.separator + "imdb"; } },
	repo { public String toString() { return data.toString() + File.separator + "repo"; } },
	jdbc { public String toString() { return repo.toString() + File.separator + "jdbc"; } }
}
