package export;

/** The export operation should be performed on an instance of this class, 
 * after having set the export strategy of choice.
 * @author crhm
 *
 */
public class ExportContext {
	
	private IExportStrategy exportStrategy;
	
	/**Sets the export strategy to be subsequently used for export(String s)
	 * @param strategy IExportStrategy to set for later export
	 */
	public void setExportStrategy(IExportStrategy strategy) {
		this.exportStrategy = strategy;
	}
	
	/**Delegates exporting to currently set exportStrategy.
	 * @param s The string to be exported
	 */
	public void export(String s) {
		this.exportStrategy.export(s);
	}

}
