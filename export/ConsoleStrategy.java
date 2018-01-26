package export;

/** IExportStrategy which outputs the Object passed as argument to export(Object o) in console
 *  on a new line.
 * @author crhm
 *
 */
public class ConsoleStrategy implements IExportStrategy {

	@Override
	public void export(Object o) {
		System.out.println(o);	
	}

}
