package fr.n7.stl.block.ast;

import java.util.List;

import fr.n7.stl.block.ast.classelement.InterfaceElement;
import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.type.Type;

public class Signature implements InterfaceElement {
	
	Type type;
	String left;
	List<ParameterDeclaration> parameters;

	public Signature(Type _type, String _left, List<ParameterDeclaration> _parameters) {
		this.type = _type;
		this.left = _left;
		this.parameters = _parameters;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ParameterDeclaration> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
