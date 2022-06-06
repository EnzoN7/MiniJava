package fr.n7.stl.block;

class Driver {

	public static void main(String[] args) throws Exception {
		Parser parser = null;
		if (args.length == 0) {
			parser = new Parser( "tests/test00.mjava" );
			//parser = new Parser( "tests/test4.java" );
			// parser = new Parser( "tests/test02.mjava" );
			parser.parse();
		} else {
			for (String name : args) {
				parser = new Parser( name );
				parser.parse();
			}
		}
	}
}