package Proyecto;

import Proyecto.Interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class UtilidadesParaListas {
    public static <U, E extends tieneLista<U>> List<E> elementosConListaVacia(List<E> listaElementos) {
        List<E> elementosConListaVacia = new ArrayList<>();
        for(E elemento : listaElementos)
            if (elemento.getLista().isEmpty())
                elementosConListaVacia.add(elemento);
        return elementosConListaVacia;
    }

    public static <E extends tieneClave<E>, T extends tieneLista<E>> boolean sePuedeInsertar(E elementoAInsertar, T elementoDondeInsertar) {
        for (E elementoLista : elementoDondeInsertar.getLista())
            if (elementoAInsertar.getClave().equals(elementoLista.getClave()))
                return false;
        return true;
    }
}
