/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo;

import java.io.IOException;

/**
 *
 * @author Martín
 */
public class Main {
    public static void main(String [] args) throws IOException{
        SistemaViajes sis = new SistemaViajes();
        sis.cargaInicial();
        sis.operar();
    }
}
