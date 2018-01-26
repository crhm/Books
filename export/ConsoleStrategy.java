package export;

public class ConsoleStrategy implements IExportStrategy {

	@Override
	public void export(String s) {
		System.out.println(s);	
	}

}
