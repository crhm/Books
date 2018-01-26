package export;

/** Interface that export strategies must implement.<br>
 * Has one method, export(String s), to implement 
 * whichever way one chooses.
 * @author crhm
 *
 */
public interface IExportStrategy {

	public void export(String s);
	
}
