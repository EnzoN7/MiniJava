package fr.n7.stl.block.ast.interfaceElement;

import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.classElement.ClassAccessRight;
import fr.n7.stl.block.ast.classElement.ClassElement;
import fr.n7.stl.block.ast.classElement.ElementModifier;
import fr.n7.stl.block.ast.classElement.Signature;
import fr.n7.stl.block.ast.expression.Expression;
import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.block.ast.type.PartialType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;
import fr.n7.stl.util.Pair;

public class InterfaceElement implements Interface, Declaration{
	 protected ClassAccessRight accessRight;
	    protected ElementModifier state;
	    protected Signature signature;
	    protected Block body;
	    protected SymbolTable parametersTable;
	    protected int offset;

	    public InterfaceElement(ClassAccessRight _accessRight, ElementModifier _state, Signature _signature, Block _body) {
	        this.accessRight = _accessRight;
	        this.state = _state;
	        this.signature = _signature;
	        this.body = _body;
	    }

	    public String toString() {
	        return this.accessRight.toString() + " " + this.state.toString() + " " + this.signature.toString() + " "
	                + body;
	    }

	    public ClassAccessRight getClassAccessRight() {
	        return this.accessRight;
	    }

	    public Signature getSignature() {
	        return this.signature;
	    }

	    @Override
	    public String getName() {
	        return this.signature.getName();
	    }

	    @Override
	    public Type getType() {
	        return this.signature.getType();
	    }

	    @Override
	    public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
	        /*
	    	String id = signature.identifiant.getLeft();
	        String name = signature.identifiant.getLeft();
	        if (this.signature.parameters != null) {
	            for (ParameterDeclaration p : this.signature.parameters) {
	                name += p.getType().toString();
	            }
	        }
	        signature.identifiant.setLeft(name);
	        if (((HierarchicalScope<Declaration>) _scope).accepts(this)) {
	            _scope.register(this);
	            SymbolTable tableParametres = new SymbolTable(_scope);
	            boolean result = true;
	            if (this.signature.getParameters() != null) {
	                for (ParameterDeclaration d : this.signature.getParameters()) {
	                    tableParametres.register(d);
	                }
	            }
	            this.parametersTable = tableParametres;
	            result = this.body.collect(tableParametres);
	            return result;
	        } else {
	            Logger.error("The interface identifier " + id + " is already defined.");
	            return false;
	        }
	        */
	    	return true;
	    }

	    @Override
	    public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
	        SymbolTable.interfaceElement = this;
	        return this.body.resolve(this.parametersTable);
	    }

	    @Override
	    public boolean checkType() {
	        boolean _result = true;
	        SymbolTable.interfaceElement = this;
	        if (this.signature.parameters != null) {
	            for(ParameterDeclaration parameterDeclaration : this.signature.parameters) {
	                if (parameterDeclaration.getType().equalsTo(AtomicType.ErrorType)) {
	                    Logger.error(parameterDeclaration + " is not compatible with parameters type.");
	                    _result = false;
	                }
	            }
	        }
			_result = _result && body.checkType();
	        return _result;
	    }

	    @Override
	    public int allocateMemory(Register _register, int _offset) {
	        this.offset = 0;
			for (ParameterDeclaration p : this.signature.getParameters()) {
				offset += p.getType().length();
			}
			this.body.allocateMemory(Register.LB, this.offset);
			return 0;
	    }

	    @Override
	    public Fragment getCode(TAMFactory _factory) {
	        Fragment _result = _factory.createFragment();
	        _result.append(this.body.getCode(_factory));
			_result.addPrefix("begin:" + this.signature.getName());
	        if (this.signature.getType() == AtomicType.VoidType){
				_result.add(_factory.createReturn(0, this.offset));
			}
			_result.addSuffix("end:" + this.signature.getName());
	        return _result;
	    }
}
