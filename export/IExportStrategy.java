package export;

/** Interface that export strategies must implement.<br>
 * Has one method, export(Object o), to implement 
 * whichever way one chooses.
 * @author crhm
 *
 */
public interface IExportStrategy {

	public void export(Object o);
	
}
