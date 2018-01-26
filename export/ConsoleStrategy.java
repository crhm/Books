package export;

/** IExportStrategy which outputs the string passed as argument to export(String s) in console
 *  on a new line.
 * @author crhm
 *
 */
public class ConsoleStrategy implements IExportStrategy {

	@Override
	public void export(String s) {
		System.out.println(s);	
	}

}
