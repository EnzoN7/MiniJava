package fr.n7.stl.block.ast.element;

import java.util.List;

import fr.n7.stl.block.ast.classElement.ClassElement;
import fr.n7.stl.block.ast.interfaceElement.Interface;
import fr.n7.stl.block.ast.interfaceElement.InterfaceElement;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

public class InterfaceDeclaration implements Element, Declaration {

	protected String name;

    protected List<Interface> interfaceElements;
    
    protected SymbolTable elementsTable;

	/**
	 * Constructor for a Main.
	 */
	public InterfaceDeclaration(String _name, List<Interface> _interfaceElements) {
		this.name = _name;
		this.interfaceElements = _interfaceElements;
	}

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String _result = "";
		_result = "interface " + this.name + " {\n";
		/**for (ClassElement c : this.classElements) {
			_result += c.toString(); 
		}*/
		_result += "}";
		return _result;
	}

	 @Override
	 public boolean collect(HierarchicalScope<Declaration> _scope) {
	    	if (((HierarchicalScope<Declaration>) _scope).accepts(this)) {
				_scope.register(this);
				SymbolTable.interfaceDeclaration = this;
				SymbolTable.interfacesDeclaration.add(this);
				this.elementsTable = new SymbolTable(_scope);
				boolean _result = true;
				for (int i = interfaceElements.size() - 1; i >= 0; i--) {
					_result = _result && this.interfaceElements.get(i).collectAndBackwardResolve(elementsTable);
				}
				return _result;
			} else {
				System.out.println("The class identifier " + this.name + " is already defined.");
				return false;
			}
	    }
	    @Override
	    public boolean resolve(HierarchicalScope<Declaration> _scope) {
	    	boolean _result = true;
			for (int i = interfaceElements.size() - 1; i >= 0; i--) {
				_result = _result && this.interfaceElements.get(i).fullResolve(elementsTable);
			}
			return _result;
	    }

	    @Override
	    public boolean checkType() {
			SymbolTable.interfaceDeclaration = this;
			boolean _result = true;
			for (int i = interfaceElements.size() - 1; i >= 0; i--) {
				_result = _result && this.interfaceElements.get(i).checkType();
			}
			return _result;
		}

	    @Override
	    public int allocateMemory(Register _register, int _offset) {
	        // TODO Auto-generated method stub
	        return 0;
	    }

	    @Override
	    public Fragment getCode(TAMFactory _factory) {
	    	Fragment _result = _factory.createFragment();
			boolean _added = false;
			SymbolTable.interfaceDeclaration = this;

			for (Interface i : this.interfaceElements) {
				_result.append(i.getCode(_factory));
				if (!_added) {
					_added = true;
					//_result.addPrefix("BEGIN:" + this.name);
				}
			}
			//_result.addSuffix("END:" + this.name);
			return _result;
	    }

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Type getType() {
			// TODO Auto-generated method stub
			return null;
		}
}
