import java.io.File;

public class FileModifier {
	private File file;
	private File[] fileList;

	public FileModifier(String path) {
		file = new File(path);
		fileList = file.listFiles();
	}

	public File[] getFiles() {
		return fileList;
	}

	public File getFile(String name) {
		File f = new File("");
		for (File file : fileList) {
			if (file.getName().equals(name))
				f = file;
		}
		return f;
	}
}
