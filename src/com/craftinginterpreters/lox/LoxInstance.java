package com.craftinginterpreters.lox;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class LoxInstance {

    private LoxClass klass;
    private final Map<String,Object> fields = new Hashtable<>();
    LoxInstance(LoxClass klass){
        this.klass = klass;
    }

    Object get(Token name){
        if(fields.containsKey(name.lexeme)){
            return fields.get(name.lexeme);
        }

        LoxFunction method = klass.findMethod(name.lexeme);

        if(method != null ) return method.bind(this);

        if(method != null) return method;

        throw new RuntimeError(name,"Undefined Property '"+name.lexeme + "'.");
    }

    void set(Token name,Object value){
        fields.put(name.lexeme,value);
    }

    @Override
    public String toString(){
        return klass.name + " instance";
    }

}
