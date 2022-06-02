package fr.n7.stl.block.ast.classelement;

import fr.n7.stl.block.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;

public class Constructor implements ClassElement, Declaration {
	
	
	
	protected FunctionDeclaration constructor;
	
	public Constructor(FunctionDeclaration _constructor) {
		this.constructor = _constructor;
	}

	@Override
	public String getName() {
		return this.constructor.getName();
	}

	@Override
	public Type getType() {
		return this.constructor.getType();
	}

	@Override
	public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
		boolean ok1 = this.constructor.collectAndBackwardResolve(_scope);
		Declaration vard = new Constructor(this.constructor);
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
		return this.constructor.fullResolve(_scope);
	}

	@Override
	public boolean checkType() {
		return this.constructor.checkType();
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
