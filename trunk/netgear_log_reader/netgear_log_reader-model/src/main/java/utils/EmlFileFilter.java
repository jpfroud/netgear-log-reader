package utils;

import java.io.File;
import java.io.FileFilter;

public final class EmlFileFilter implements FileFilter {
	@Override
	public boolean accept(File pathname) {
		return pathname.getName().endsWith(".eml");
	}
}