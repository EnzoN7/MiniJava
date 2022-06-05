package fr.n7.stl.block.ast.classelement;

import fr.n7.stl.block.Elements;
import fr.n7.stl.block.ast.instruction.declaration.FunctionDeclaration;

public class Main implements Elements {
	
	FunctionDeclaration funcDecl;
	
	public Main(FunctionDeclaration _funcDecl) {
		this.funcDecl = _funcDecl;
	}

}
