package fr.n7.stl.block.ast.classelement;

import fr.n7.stl.block.ast.instruction.declaration.VariableDeclaration;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;

public class Attribute implements ClassElement, Declaration {
	
	
	protected VariableDeclaration var; 
	
	protected boolean right_final;
	
	protected boolean right_static;
	
	
	public Attribute(VariableDeclaration _var, boolean _final, boolean _static ) {
		this.var = _var;
		this.right_final = _final;
		this.right_static = _static;
	}

	@Override
	public String getName() {
		return this.var.getName();
	}

	@Override
	public Type getType() {
		return this.var.getType();
	}

	@Override
	public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
		boolean ok1 = this.var.collectAndBackwardResolve(_scope);
		Declaration vard = new Attribute(this.var,this.right_final,this.right_static);
		boolean ok2 = _scope.accepts(vard);
		if (ok1) {
			Logger.error("Erreur nom fonction ");
			return false;
		} else if (ok2){
			_scope.register(vard);
			ok1 = true;
		} else {
			return false;
		}
		return ok1 && ok2;
	}

	@Override
	public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
		return this.var.fullResolve(_scope);
	}

	@Override
	public boolean checkType() {
		return this.var.checkType();
	}

	@Override
	public int allocateMemory(Register _register, int _offset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		return null;
	}

}
