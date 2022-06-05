package fr.n7.stl.block.ast;

import java.util.List;

import fr.n7.stl.block.ast.instruction.Instruction;
import fr.n7.stl.block.Elements;
import fr.n7.stl.block.ast.classelement.*;
import fr.n7.stl.block.ast.instruction.Return;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.block.ast.type.Type;

public class Clazz implements Elements {
	
	protected List<ClassElement> classElements;
	
	private String name;
	
	protected HierarchicalScope<Declaration> _scope2;
	
	public Clazz (String _name, List<ClassElement> _classElements) {
		this.name = _name;
		this.classElements = _classElements;
	}
	
	public boolean collect(HierarchicalScope<Declaration> _scope) {
		this._scope2 = new SymbolTable(_scope);
		boolean ok = true;
		for( ClassElement ce : this.classElements) {
			ok = ok && ce.collectAndBackwardResolve(_scope2) ;
		}
		System.out.println("Locales : " + _scope);
		return ok;
	}
	
	public boolean resolve(HierarchicalScope<Declaration> _scope) {
		boolean ok = true;
		for( ClassElement ce : this.classElements) {
			ok = ok && ce.fullResolve(this._scope2) ;
		}
		
		return ok;
	}
	
	public boolean checkType() {
		boolean ok =true;
		for( ClassElement ce : this.classElements) {
			ok = ok == ce.checkType();
			if (ok == false) {
				return ok;
			}
		}
		return ok;
	}
	
}
