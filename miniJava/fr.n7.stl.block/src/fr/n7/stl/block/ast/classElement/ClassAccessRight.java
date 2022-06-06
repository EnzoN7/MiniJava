package fr.n7.stl.block.ast.classElement;

public enum ClassAccessRight {
	PUBLIC,
    PRIVATE,
    PROTECTED;

    public String toString() {
        switch (this) {
	        case PUBLIC:
	            return "public";
            case PRIVATE:
                return "private";
            case PROTECTED:
                return "protected";
            
            default:
                throw new IllegalArgumentException("Error : Unknown ClassAccessRight token");
        }
    }

}
