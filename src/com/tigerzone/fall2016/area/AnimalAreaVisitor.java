package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Tiger;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public interface AnimalAreaVisitor {
    public void visit(Crocodile crocodile);
    public void visit(Tiger tiger);
}
