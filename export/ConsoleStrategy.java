package export;

/** IExportStrategy which outputs the object passed as argument to export(Object o) in console
 *  on a new line, in its toString() form.
 * @author crhm
 *
 */
public class ConsoleStrategy implements IExportStrategy {

	@Override
	public void export(Object o) {
		System.out.println(o);	
	}

}
