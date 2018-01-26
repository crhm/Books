package export;

public class ExportContext {
	
	private IExportStrategy exportStrategy;
	
	public void setExportStrategy(IExportStrategy strategy) {
		this.exportStrategy = strategy;
	}
	
	public void export(String s) {
		this.exportStrategy.export(s);
	}

}
