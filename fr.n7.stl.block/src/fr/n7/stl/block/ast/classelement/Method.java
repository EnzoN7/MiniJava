package fr.n7.stl.block.ast.classelement;

import java.util.List;

import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;

public class Method implements ClassElement, Declaration {
	
	protected boolean right_final;
	protected boolean right_static;
	protected FunctionDeclaration function;
	

	public Method(FunctionDeclaration _function, boolean _final, boolean _static ) {
		this.function = _function;
		this.right_final = _final;
		this.right_static = _static;
	}
	
	@Override
	public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
		boolean ok1 = this.function.collectAndBackwardResolve(_scope);
		Declaration vard = new Method(this.function,this.right_final,this.right_static);
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
		return this.function.fullResolve(_scope);
	}

	@Override
	public boolean checkType() {
		return this.function.checkType();
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

	@Override
	public String getName() {
		return this.function.getName();
	}

	@Override
	public Type getType() {
		return this.function.getType();
	}

}
