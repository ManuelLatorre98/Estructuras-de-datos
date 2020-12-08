/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.proposito.especifico;
import proposito.especifico.Diccionario;
/**
 *
 * @author Martín
 */
public class TestDiccionario {
    public static void main (String [] args){
        Diccionario a = new Diccionario();
        
        System.out.println("es vacio: "+a.esVacio());
        
        System.out.println("insertar clave: 'FAI-030' datos: 'Martín' --> "+a.insertar("FAI-030", "Martín"));
        System.out.println("insertar clave: 'FAI-026' datos: 'Micaela' --> "+a.insertar("FAI-026", "Micaela"));
        System.out.println("insertar clave: 'FAI-004' datos: 'Macarena' --> "+a.insertar("FAI-004", "Macarena"));
        System.out.println("insertar clave: 'FAI-018' datos: 'Miguel' --> "+a.insertar("FAI-018", "Miguel"));
        System.out.println("insertar clave: 'FAI-008' datos: 'Karina' --> "+a.insertar("FAI-008", "Karina"));
        System.out.println("insertar clave: 'FAI-010' datos: 'Chimuelo' --> "+a.insertar("FAI-010", "Chimuelo"));
        System.out.println("insertar clave: 'FAI-020' datos: 'Kobu' --> "+a.insertar("FAI-020", "Kobu"));
        System.out.println("insertar clave: 'FAI-020' datos: 'Kobu' --> "+a.insertar("FAI-020", "Kobu"));
        System.out.println("estructura: \n"+a.toString());
        
        System.out.println("es vacio: "+a.esVacio());
        System.out.println("listar claves: "+a.listarClaves());
        System.out.println("listar datos: "+a.listarDatos());
        
        System.out.println("obtener dato de clave 'FAI-000': "+a.obtenerDato("FAI-000"));
        System.out.println("obtener dato de clave 'FAI-010': "+a.obtenerDato("FAI-010"));
        
        System.out.println("existe 'FAI-030'? "+a.existeClave("FAI-030"));
        System.out.println("eliminar : "+a.eliminar("FAI-030"));
        System.out.println("existe 'FAI-030'? "+a.existeClave("FAI-030"));
        System.out.println("eliminar : "+a.eliminar("FAI-030"));
        System.out.println("listar claves: "+a.listarClaves());
        System.out.println("estructura: \n"+a.toString());
        
        System.out.println("existe 'FAI-018'? "+a.existeClave("FAI-018"));
        System.out.println("eliminar : "+a.eliminar("FAI-018"));
        System.out.println("existe 'FAI-018'? "+a.existeClave("FAI-018"));
        System.out.println("eliminar : "+a.eliminar("FAI-018"));
        System.out.println("listar claves: "+a.listarClaves());
        System.out.println("estructura: \n"+a.toString());
    }
}
