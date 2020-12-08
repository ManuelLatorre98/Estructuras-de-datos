/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo;
import grafos.GrafoEtiquetado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import proposito.especifico.Diccionario;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import lineales.dinamicas.Lista;
/**
 *
 * @author Martín
 */
public class SistemaViajes {
    GrafoEtiquetado aeropuertos;
    Diccionario vuelos;
    Diccionario clientes;
    HashMap clientesPasajes;
    
    //Método principal
    public SistemaViajes()throws FileNotFoundException, IOException{
        aeropuertos = new GrafoEtiquetado();
        vuelos = new Diccionario();
        clientes = new Diccionario();
        clientesPasajes = new HashMap();
    }
    
    //Carga inicial
    public void cargaInicial() throws FileNotFoundException, IOException {
        File archivoTexto = new File("C:\\Users\\Martín\\Desktop\\TPO\\cargaInicial.txt");
        BufferedReader br = new BufferedReader(new FileReader(archivoTexto));
        String cadena;
        while ( (cadena = br.readLine()) != null) {
            StringTokenizer token = new StringTokenizer(cadena.substring(3), ", ");
            switch (cadena.charAt(0)) {
                case 'A': {
                    String clave = token.nextToken(),
                            ciudad = token.nextToken(),
                            telefono = token.nextToken();
                    altaAeropuerto(clave, ciudad, telefono);
                } break;
                case 'V': {
                    String clave = token.nextToken(),
                            claveAeropuertoOrigen = token.nextToken(),
                            claveAeropuertoDestino = token.nextToken(),
                            horaSalida = token.nextToken(),
                            horaLlegada = token.nextToken(),
                            fecha = token.nextToken(),
                            cantAsientos = token.nextToken();
                    Lista temp = aeropuertos.listarEnAnchura();
                    int i=0;
                    Aeropuerto origen = null,
                            destino = null,
                            auxO = new Aeropuerto(claveAeropuertoOrigen),
                            auxD = new Aeropuerto(claveAeropuertoDestino);
                    while (origen == null || destino == null){
                        Aeropuerto aerop = (Aeropuerto) temp.recuperar(i);
                        if (origen == null && aerop!=null && aerop.equals(auxO))
                            origen = aerop;
                        if (destino == null &&  aerop!=null && aerop.equals(auxD))
                            destino = aerop;
                        i++;
                    }
                    altaVuelo(clave, origen, destino, horaSalida, horaLlegada, fecha, Integer.parseInt(cantAsientos), 0);
                }break;
                case 'C': {
                    String tipoDni = token.nextToken(),
                            numeroDni = token.nextToken(),
                            nombre = token.nextToken(),
                            apellido = token.nextToken(),
                            fechaNac = token.nextToken(),
                            domicilio = token.nextToken(),
                            telefono = token.nextToken();
                    altaCliente(tipoDni, numeroDni, nombre, apellido, fechaNac, domicilio, telefono);
                }break;
                case 'P': {
                    String tipoDni = token.nextToken(),
                            numDni = token.nextToken(),
                            claveVuelo = token.nextToken(),
                            fecha = token.nextToken(),
                            asiento = token.nextToken(),
                            estado = token.nextToken();
                    altaPasaje((Cliente) clientes.obtenerDato(new ClaveCliente(tipoDni, numDni)), (Vuelo) vuelos.obtenerDato(new ClaveVuelo(claveVuelo)), fecha, asiento, estado);
                }break;
                default: ;break;
            }
        }
        escribirEnLog(mostrarSistema());
    }
        
    //Menú
    public String menu(){
        System.out.println("EDAT VIAJES.COM \n"+
                //ABM aeropuertos
                "Si quiere dar de alta un aeropuerto, ingrese 1\n"+
                "Si quiere dar de baja un aeropuerto, ingrese 2\n"+
                "Si quiere modificar un aeropuerto, ingrese 3\n"+
                //ABM clientes
                "Si quiere dar de alta un cliente, ingrese 4\n"+
                "Si quiere dar de baja un cliente, ingrese 5\n"+
                "Si quiere modificar un cliente, ingrese 6\n"+
                //ABM vuelos
                "Si quiere dar de alta un vuelo, ingrese 7\n"+
                "Si quiere dar de baja un vuelo, ingrese 8\n"+
                "Si quiere modificar un vuelo, ingrese 9\n"+
                //ABM pasajes
                "Si quiere dar de alta un pasaje, ingrese 10\n"+
                "Si quiere dar de baja un pasaje, ingrese 11\n"+
                "Si quiere modificar un pasaje, ingrese 12\n"+
                //Consultas sobre clientes
                "Si quiere información de un cliente, ingrese 13\n"+
                "Si quiere ver las ciudades que visitó un cliente, ingrese 14\n"+
                //Consultas sobre vuelos
                "Si quiere información de un vuelo, ingrese 15\n"+
                "Si quiere ver los códigos de vuelo entre otros dos, ingrese 16\n"+
                //Consultas sobre tiempos de viaje
                "Si quiere ver si un cliente puede llegar de un aerpuerto A a otro B en una cantidad determinada de escalas, ingrese 17\n"+
                "Si quiere obtener el camino de menor tiempo de un aerpuerto A a otro B, ingrese 18\n"+
                //Promoción a clientes fieles
                "Si quiere ver una lista ordenada de mayor a menor de clientes según la cantidad de pasajes comprados, ingrese 19\n"+
                //Mostrar sistema
                "Si quiere ver el sistema completo, ingrese 20\n"+
                
                "Si quiere finalizar la operación, ingrese 0\n");
        Scanner opcion = new Scanner(System.in);
        String salida = opcion.nextLine();
        if (salida.equals("0")) {
            try {
                escribirEnLog(mostrarSistema());
            } catch (IOException ex) {
                Logger.getLogger(SistemaViajes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return salida;
    }
    
    //ABM aeropuertos
    public boolean altaAeropuerto(String clave, String ciudad, String telefono) {
        Aeropuerto a = new Aeropuerto(clave, ciudad, telefono);
        return aeropuertos.insertarVertice(a);
    }
    public boolean bajaAeropuerto(String clave) {
        Aeropuerto a = new Aeropuerto(clave);
        boolean salida = eliminarVuelosDeAeropuerto(a);
        if (salida){
            salida = aeropuertos.eliminarVertice(new Aeropuerto(clave));
        }
        return salida;
    }
    private boolean eliminarVuelosDeAeropuerto(Aeropuerto a){
        boolean salida = false;
        Lista lis = vuelos.listarDatos();
        int i = 1;
        while (i<=lis.longitud()){
            Vuelo v = (Vuelo) lis.recuperar(i);
            if (v.getAeropuertoOrigen().equals(a) || v.getAeropuertoDestino().equals(a)) {
                bajaVuelo(v.getClave());
                salida = true;
            }
            i++;
        }
        return salida;
    }
    public boolean modificarAeropuerto(Aeropuerto a, String newTelefono) {
        boolean salida = false;
        if (a!=null){
            a.setTel(newTelefono);
            salida = true;
        }
        return salida;
    }
    
    //ABM clientes
    public boolean altaCliente(String tipoDni, String numeroDni, String nombre, String apellido, String fechaNac, String domicilio, String telefono){
        Cliente aux = new Cliente(tipoDni, numeroDni, nombre, apellido, fechaNac, domicilio, telefono);
        boolean salida = clientes.insertar(new ClaveCliente(tipoDni, numeroDni), aux);
        if (salida) {
            clientesPasajes.put(aux, new Lista());
        }
        return salida;
    }
    public boolean bajaCliente(String tipoDni, String numDni){
        boolean salida = false;
        if (clientes.existeClave(new ClaveCliente(tipoDni, numDni))){
            Cliente aux = (Cliente) clientes.obtenerDato(new ClaveCliente(tipoDni, numDni));
            clientesPasajes.remove(aux);
            salida = clientes.eliminar(new ClaveCliente(tipoDni, numDni));
        }
        return salida;
    }
    public boolean modificarCliente(Cliente c, String nombre, String apellido, String fechaNac, String domicilio, String telefono){
        boolean salida = false;
        if (c!=null){
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFechaNac(fechaNac);
            c.setDomicilio(domicilio);
            c.setTelefono(telefono);
            salida = true;
        }
        return salida;
    }
    
    //ABM vuelos
    public boolean altaVuelo(String clave, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, String horaSalida, String horaLlegada, String fecha, int cantAsientos, int cantAsientosVendidos){
        Vuelo aux;
        ClaveVuelo cl = new ClaveVuelo(clave);
        if (vuelos.existeClave(cl))
            aux = (Vuelo) vuelos.obtenerDato(cl);
        else {
            aux = new Vuelo (clave, aeropuertoOrigen, aeropuertoDestino, horaSalida, horaLlegada, new Lista());
            int etiqueta = restarHoras(horaSalida, horaLlegada);
            aeropuertos.insertarArco(aeropuertoOrigen, aeropuertoDestino, etiqueta);
        }
        aux.getListaVuelos().insertar(new VueloPorDia(fecha, cantAsientos, cantAsientosVendidos), 1);
        return vuelos.insertar(new ClaveVuelo(clave), aux);
    }
    private int restarHoras(String horaSalida, String horaLlegada){
        int horaInicio = Integer.parseInt(horaSalida.substring(0, horaSalida.indexOf(":"))),
                minInicio = Integer.parseInt(horaSalida.substring(horaSalida.indexOf(":")+1, 4)),
                horaFin = Integer.parseInt(horaLlegada.substring(0, horaLlegada.indexOf(":"))),
                minFin = Integer.parseInt(horaLlegada.substring(horaLlegada.indexOf(":")+1, 4)),
                salida = (horaFin*60+minFin) - (horaInicio*60+minInicio);
        if (horaSalida.compareTo(horaLlegada) > 1){
            salida += 24*60;
        }
        return salida;
    }
    public boolean bajaVuelo(String clave){
        boolean salida = false;
        ClaveVuelo cl = new ClaveVuelo(clave);
        Vuelo v = (Vuelo) vuelos.obtenerDato(cl);
        if (v!=null){
            salida = vuelos.eliminar(cl);
            if (salida){
                //Elimina el arco
                int etiq = restarHoras(v.getHoraSalida(), v.getHoraLlegada());
                Object origen = v.getAeropuertoOrigen(), destino = v.getAeropuertoDestino();
                salida = aeropuertos.eliminarArco(origen, destino, etiq);
            }
        }
        return salida;
    }
    public boolean modificarVuelo(Vuelo v, String horaLlegada, String horaSalida){
        boolean salida = false;
        if (v!=null) {
            aeropuertos.eliminarArco(v.getAeropuertoOrigen(), v.getAeropuertoDestino(), restarHoras(v.getHoraSalida(), v.getHoraLlegada()));
            v.setHoraLlegada(horaLlegada);
            v.setHoraSalida(horaSalida);
            aeropuertos.insertarArco(v.getAeropuertoOrigen(), v.getAeropuertoDestino(), restarHoras(horaSalida, horaLlegada));
            salida = true;
        }
        return salida;
    }
    
    //ABM pasajes
    public boolean altaPasaje(Cliente c, Vuelo vuelo, String fecha, String asiento, String estado){
        Lista aux = (Lista) clientesPasajes.get(c);
        boolean salida = (estado.equals("PENDIENTE") || estado.equals("CANCELADO") || estado.equals("VOLADO"));
        if (salida && vuelos.existeClave(new ClaveVuelo(vuelo.getClave())) && clientes.existeClave(new ClaveCliente(c.getTipoDni(), c.getNumDni()))) {
            int i = 0;
            VueloPorDia v = null;
            boolean encontrado = false;
            Lista lis = vuelo.getListaVuelos();
            while (!encontrado && i<=lis.longitud()){
                v = (VueloPorDia) lis.recuperar(i);
                if (v!=null)
                    encontrado = v.getFecha().equals(fecha);
                i++;
            }
            if(encontrado && v.getCantAsientos()>v.getCantAsientosVendidos()){
                if (aux==null)
                    aux = new Lista();
                aux.insertar(new Pasaje(vuelo, fecha, asiento, estado), aux.longitud()+1);    
                clientesPasajes.put(c, aux);
                v.setCantAsientosVendidos(v.getCantAsientosVendidos()+1);
            } else 
                salida = false;
        } else
            salida = false;
        return salida;
    }
    public boolean bajaPasaje(Cliente c, Pasaje p){
        boolean salida = false;
        Lista lsPasajesCliente = (Lista) clientesPasajes.get(c),
                lsAux = p.getVuelo().getListaVuelos();
        String fecha = p.getFecha();
        int i = 1;
        boolean encontrado = false;
        VueloPorDia vuelo = null, aux;
        while (i<=lsAux.longitud() && !encontrado){
            aux = (VueloPorDia) lsAux.recuperar(i);
            if (aux.getFecha().equals(fecha)){
                vuelo = aux;
                encontrado = true;
            }
            i++;
        }
        if (vuelo!=null) {
            vuelo.setCantAsientosVendidos(vuelo.getCantAsientosVendidos()-1);
            lsPasajesCliente.eliminar(lsPasajesCliente.localizar(p));
            clientesPasajes.put(c, lsPasajesCliente);
            salida = true;
        }
        return salida;
    }
    public boolean modificarPasaje(Pasaje p, String fecha, String asiento, String estado, String tipoDni, String numDni){
        boolean salida = (estado.equals("PENDIENTE") || estado.equals("CANCELADO") || estado.equals("VOLADO"));
        if (salida && p!=null) {
            p.setFecha(fecha);
            p.setAsiento(asiento);
            p.setEstado(estado);
        } else
            salida = false;
        return salida;
    }
    
    //Consultas sobre clientes
    public String contactoCliente(String tipoDni, String numDni){
        String salida = "";
        ClaveCliente clave = new ClaveCliente(tipoDni, numDni);
        Cliente c = (Cliente) clientes.obtenerDato(clave);
        if (c != null){
            salida += "Nombre: "+c.getNombre()+" "+c.getApellido()+"\n"+
            "Domicilio: "+c.getDomicilio()+"\n"+
            "Teléfono: "+c.getTelefono()+"\n";
            Lista lis = (Lista) clientesPasajes.get(c);
            String cadena = "";
            int i = 1;
            while (i<=lis.longitud()){
                Pasaje pas = (Pasaje) lis.recuperar(i);
                if (pas.getEstado().equals("PENDIENTE"))
                    cadena += pas.getVuelo().getClave()+" ";
                i++;
            }
            salida += "Vuelos pendientes: "+cadena;
        } else
            salida = "No existe el cliente";
        return salida;
    }
    public String ciudadesVisitadas(Cliente cl){
        String salida = "No existe el cliente";
        if (clientes.existeClave(new ClaveCliente(cl.getTipoDni(), cl.getNumDni()))){
            Lista aux = (Lista) clientesPasajes.get(cl);
            salida = "Ciudades visitadas: ";
            int i=1;
            while (i<=aux.longitud()){
                Pasaje pas = (Pasaje) aux.recuperar(i);
                if (pas.getEstado().equals("VOLADO")){
                    Vuelo v = pas.getVuelo();
                    salida += v.getAeropuertoDestino().getCiudad()+", ";
                }
                i++;   
            }
        }
        return salida;
    }
    
    //Consultas sobre vuelos
    public String infoVuelo(ClaveVuelo clave, String fecha){
        String salida = "No existe el vuelo";
        if (vuelos.existeClave(clave)){
            Vuelo vuelo = (Vuelo) vuelos.obtenerDato(clave);
            Lista lis = vuelo.getListaVuelos();
            boolean encontrado = false;
            int i = 1;
            while (!encontrado && i<=lis.longitud()){
                VueloPorDia aux = (VueloPorDia) lis.recuperar(i);
                encontrado = aux.getFecha().equals(fecha);
                i++;
                if (encontrado){
                    salida = "Clave: "+vuelo.getClave()+"\n"+
                        "Aeuropuerto Origen: "+vuelo.getAeropuertoOrigen()+"\n"+
                        "Aeropuerto Destino: "+vuelo.getAeropuertoDestino()+"\n"+
                        "Hora de Salida: "+vuelo.getHoraSalida()+"\n"+
                        "Hora de Llegada: "+vuelo.getHoraLlegada()+"\n"+
                        "Fecha: "+aux.getFecha()+"\n"+
                        "Cantidad de asientos: "+aux.getCantAsientos()+"\n"+
                        "Cantidad de asientos vendidos: "+aux.getCantAsientosVendidos();
                }
            }
        }
        return salida;
    }
    public String codigosEntre(ClaveVuelo clave1, ClaveVuelo clave2){
        String salida = "No existe un vuelo";
        if (vuelos.existeClave(clave1) && vuelos.existeClave(clave2)){
            salida = "Claves de vuelo entre "+clave1.getClave()+" y "+
                clave2.getClave()+": "+clave1.getClave()+" ";
            Lista lis = vuelos.listarClaves();
            int i = 1;
            while (i<=lis.longitud()){
                ClaveVuelo claveAux = (ClaveVuelo) lis.recuperar(i);
                if (claveAux.compareTo(clave1)>0 && claveAux.compareTo(clave2)<0)
                    salida += claveAux.getClave()+" ";
                i++;
            }
            salida += clave2.getClave();
        }
        return salida;
    }
    
    //Consultas sobre tiempos de viaje
    public boolean recorreEn(Aeropuerto a, Aeropuerto b, int x){
        return aeropuertos.caminoMasCorto(a, b).longitud()-1 <= x;
    }
    public Lista caminoMenorTiempo(Aeropuerto a, Aeropuerto b){
        Lista salida = new Lista();
        if (aeropuertos.existeCamino(a, b)){
            salida = aeropuertos.caminoMasCortoPorEtiquetas(a, b);
        }
        return salida;
    }
    //Promoción a clientes fieles
    public Lista promocionClientes(){
        Lista cl = clientes.listarDatos(), pasajes, salida;
        int i=1, cantPasajes;
        Diccionario aux = new Diccionario();
        Cliente c;
        while (i<=cl.longitud()){
            c = (Cliente) cl.recuperar(i);
            pasajes = (Lista) clientesPasajes.get(c);
            cantPasajes = pasajes.longitud();
            aux.insertar(cantPasajes, c.getTipoDni()+" "+c.getNumDni());
            i++;
        }
        salida = aux.listarDatos();
        return salida;
    }
    //Mostrar sistema
    public String mostrarSistema(){
        String salida = "Estructura de aeropuertos:\n"+aeropuertos.toString()+"\n"+
                "Estructura de clientes: \n"+clientes.toString()+"\n"+
                "Estructura de vuelos: \n"+vuelos.toString()+"\n"+
                "Estructura de pasajes: \n"+clientesPasajes.toString()+"\n"+
                "\nVuelos por fecha: \n";
        Lista aux = vuelos.listarClaves();
        int i=0;
        while (i<=aux.longitud()){
            ClaveVuelo c = (ClaveVuelo) aux.recuperar(i);
            if (c!=null){
                Vuelo v = (Vuelo) vuelos.obtenerDato(c);
                salida += v.getClave()+": "+v.getListaVuelos().toString()+", ";
            }
            i++;
        }
        return salida+"\n";
    }
    
    //LOG
    private void escribirEnLog(String cadena) throws IOException{
        File log = new File("C:\\Users\\Martín\\Desktop\\TPO\\log.txt");
        FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(cadena+"\n\n");
        bw.close();
    }
    
    //Operaciones según opción elegida
    public void operar() throws IOException{
        String opcion;
        boolean temp;
        do {
            opcion = menu();
            Scanner x = new Scanner(System.in);
            switch(opcion){
                case ("1"): {
                    System.out.println("Ingrese una clave");
                    String clave = x.nextLine();
                    System.out.println("Ingrese una ciudad");
                    String ciudad = x.nextLine();
                    System.out.println("Ingrese un telefono");
                    String telefono = x.nextLine();
                    temp = altaAeropuerto(clave, ciudad, telefono);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se crea el aeropuerto "+clave);
                }break;
                case ("2"): {
                    System.out.println("Ingrese una clave");
                    String clave = x.nextLine();
                    temp = bajaAeropuerto(clave);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se elimina el aeropuerto "+clave);
                }break;
                case ("3"): {
                    System.out.println("Ingrese una clave");
                    String clave = x.nextLine();
                    Lista ls = aeropuertos.listarEnAnchura();
                    int i=0;
                    Aeropuerto a = null, aux;
                    while (a==null){
                        aux = (Aeropuerto) ls.recuperar(i);
                        if (aux != null && aux.getClave().equals(clave))
                            a = aux;
                        i++;
                    }
                    System.out.println("Ingrese un telefono");
                    String newTelefono = x.nextLine();
                    temp = modificarAeropuerto(a, newTelefono);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se modifica el aeropuerto "+clave);
                }break;
                case ("4"): {
                    System.out.println("Ingrese un tipo de dni");
                    String tipoDni = x.nextLine();
                    System.out.println("Ingrese un numero de dni");
                    String numeroDni = x.nextLine();
                    System.out.println("Ingrese un nombre");
                    String nombre = x.nextLine();
                    System.out.println("Ingrese un apellido");
                    String apellido = x.nextLine();
                    System.out.println("Ingrese una fecha de nacimiento");
                    String fechaNac = x.nextLine();
                    System.out.println("Ingrese un domicilio");
                    String domicilio = x.nextLine();
                    System.out.println("Ingrese una telefono");
                    String telefono = x.nextLine();
                    temp = altaCliente(tipoDni, numeroDni, nombre, apellido, fechaNac, domicilio, telefono);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se crea el cliente "+tipoDni+" "+numeroDni);
                }break;
                case ("5"): {
                    System.out.println("Ingrese un tipo de dni");
                    String tipoDni = x.nextLine();
                    System.out.println("Ingrese un numero de dni");
                    String numDni = x.nextLine();
                    temp = bajaCliente(tipoDni, numDni);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se elimina el aeropuerto "+tipoDni+" "+numDni);
                }break;
                case ("6"): {
                    System.out.println("Ingrese un tipo de dni");
                    String tipoDni = x.nextLine();
                    System.out.println("Ingrese una numero de dni");
                    String numDni = x.nextLine();
                    Cliente c = (Cliente) clientes.obtenerDato(new ClaveCliente(tipoDni, numDni));
                    System.out.println("Ingrese una nombre");
                    String nombre = x.nextLine();
                    System.out.println("Ingrese una apellido");
                    String apellido = x.nextLine();
                    System.out.println("Ingrese una fecha de nacimiento");
                    String fechaNac = x.nextLine();
                    System.out.println("Ingrese un domicilio");
                    String domicilio = x.nextLine();
                    System.out.println("Ingrese un telefono");
                    String telefono = x.nextLine();
                    temp = modificarCliente(c, nombre, apellido, fechaNac, domicilio, telefono);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se modifica el cliente "+tipoDni+" "+numDni);
                }break;
                case ("7"): {
                    System.out.println("Ingrese la clave de vuelo");
                    String clave = x.nextLine();
                    System.out.println("Ingrese clave del aeropuerto origen");
                    String claveAeropuertoOrigen = x.nextLine();
                    System.out.println("Ingrese clave del aeropuerto destino");
                    String claveAeropuertoDestino = x.nextLine();
                    Lista temp2 = aeropuertos.listarEnAnchura();
                        int i=0;
                        Aeropuerto origen = null,
                                destino = null,
                                auxO = new Aeropuerto(claveAeropuertoOrigen),
                                auxD = new Aeropuerto(claveAeropuertoDestino);
                        while (origen == null || destino == null){
                            Aeropuerto aerop = (Aeropuerto) temp2.recuperar(i);
                            if (origen == null && aerop!=null && aerop.equals(auxO))
                                origen = aerop;
                            if (destino == null && aerop!=null && aerop.equals(auxD))
                                destino = aerop;
                            i++;
                        }
                    System.out.println("Ingrese hora de salida");
                    String horaSalida = x.nextLine();
                    System.out.println("Ingrese hora de llegada");
                    String horaLlegada = x.nextLine();
                    System.out.println("Ingrese la fecha de vuelo");
                    String fecha = x.nextLine();
                    System.out.println("Ingrese la cantidad de asientos");
                    String cantAsientos = x.nextLine();
                    temp = altaVuelo(clave, origen, destino, horaSalida, horaLlegada, fecha, Integer.parseInt(cantAsientos), 0);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se crea el vuelo "+clave);
                }break;
                case ("8"): {
                    System.out.println("Ingrese la clave de vuelo");
                    String clave = x.nextLine();
                    temp = bajaVuelo(clave);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se elimina el vuelo "+clave);
                }break;
                case ("9"): {
                    System.out.println("Ingrese la clave de vuelo");
                    String clave = x.nextLine();
                    Vuelo v = (Vuelo) vuelos.obtenerDato(new ClaveVuelo(clave));
                    System.out.println("Ingrese la hora de salida");
                    String horaSalida = x.nextLine();
                    System.out.println("Ingrese la hora de llegada");
                    String horaLlegada = x.nextLine();
                    temp = modificarVuelo(v, horaLlegada, horaSalida);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se modifica el vuelo "+clave);
                }break;
                case ("10"): {
                    System.out.println("Ingrese el tipo de dni del cliente");
                    String tipoDni = x.nextLine();
                    System.out.println("Ingrese el número de dni del cliente");
                    String numDni = x.nextLine();
                    Cliente c = (Cliente) clientes.obtenerDato(new ClaveCliente(tipoDni, numDni));
                    System.out.println("Ingrese la clave de vuelo");
                    String claveV = x.nextLine();
                    Vuelo vuelo = (Vuelo) vuelos.obtenerDato(new ClaveVuelo(claveV));
                    System.out.println("Ingrese la fecha del pasaje");
                    String fecha = x.nextLine();
                    System.out.println("Ingrese el asiento");
                    String asiento = x.nextLine();
                    System.out.println("Ingrese el estado del pasaje (PENDIENTE, CANCELADO O VOLADO)");
                    String estado = x.nextLine();
                    temp = altaPasaje(c, vuelo, fecha, asiento, estado);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("El cliente "+tipoDni+" "+numDni+" compra un pasaje para el vuelo "+claveV);
                }break;
                case ("11"): {
                    System.out.println("Ingrese el tipo de dni del cliente");
                    String tipoDni = x.nextLine();
                    System.out.println("Ingrese el número de dni del cliente");
                    String numDni = x.nextLine();
                    Cliente c = (Cliente) clientes.obtenerDato(new ClaveCliente(tipoDni, numDni));
                    System.out.println("Ingrese la clave de vuelo del pasaje");
                    String claveV = x.nextLine();
                    Lista ls = (Lista) clientesPasajes.get(c);
                    Pasaje p=null, aux;
                    int i = 1;
                    while(i<=ls.longitud()){
                        aux = (Pasaje) ls.recuperar(i);
                        if (aux!=null && aux.getVuelo().getClave().equals(claveV))
                            p = aux;
                        i++;
                    }
                    temp = bajaPasaje(c, p);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se elimina el pasaje para el vuelo "+claveV+" del cliente "+tipoDni+" "+numDni);
                }break;
                case ("12"): {
                    System.out.println("Ingrese el tipo de dni del cliente");
                    String tipoDni = x.nextLine();
                    System.out.println("Ingrese el número de dni del cliente");
                    String numDni = x.nextLine();
                    Cliente c = (Cliente) clientes.obtenerDato(new ClaveCliente(tipoDni, numDni));
                    System.out.println("Ingrese la clave de vuelo del pasaje");
                    String claveV = x.nextLine();
                    Lista ls = (Lista) clientesPasajes.get(c);
                    Pasaje p=null, aux;
                    int i= 0;
                    while(i<=ls.longitud()){
                        aux = (Pasaje) ls.recuperar(i);
                        if (aux!=null && aux.getVuelo().getClave().equals(claveV))
                            p = aux;
                        i++;
                    }
                    System.out.println("Ingrese la fecha del vuelo");
                    String fecha = x.nextLine();
                    System.out.println("Ingrese el asiento");
                    String asiento = x.nextLine();
                    System.out.println("Ingrese el estado del pasaje (VOLADO, PENDIENTE o CANCELADO");
                    String estado = x.nextLine();
                    temp = modificarPasaje(p, fecha, asiento, estado, tipoDni, numDni);
                    System.out.println(temp);
                    if (temp)
                        escribirEnLog("Se modifica el pasaje para el vuelo "+claveV+" del cliente "+tipoDni+" "+numDni);
                }break;
                case ("13"): {
                    System.out.println("Ingrese el tipo de dni");
                    String tipoDni = x.nextLine();
                    System.out.println("Ingrese el número de dni");
                    String numDni = x.nextLine();
                    System.out.println(contactoCliente(tipoDni, numDni));
                }break;
                case ("14"): {
                    System.out.println("Ingrese el tipo de dni del cliente");
                    String tipoDni = x.nextLine();
                    System.out.println("Ingrese el número de dni del cliente");
                    String numDni = x.nextLine();
                    if (clientes.existeClave(new ClaveCliente (tipoDni, numDni))){
                        Cliente cl = (Cliente) clientes.obtenerDato(new ClaveCliente(tipoDni, numDni));
                        System.out.println(ciudadesVisitadas(cl));
                    } else
                        System.out.println("No existe el cliente");
                }break;
                case ("15"): {
                    System.out.println("Ingrese la clave del vuelo");
                    String clave = x.nextLine();
                    System.out.println("Ingrese la fecha del vuelo");
                    String fecha = x.nextLine();
                    System.out.println(infoVuelo(new ClaveVuelo(clave), fecha));
                }break;
                case ("16"): {
                    System.out.println("Ingrese la clave del primer vuelo");
                    String clave1 = x.nextLine();
                    System.out.println("Ingrese la clave del segundo vuelo");
                    String clave2 = x.nextLine();
                    System.out.println(codigosEntre(new ClaveVuelo(clave1), new ClaveVuelo(clave2)));
                }break;
                case ("17"): {
                    System.out.println("Ingrese la clave del primer aeropuerto");
                    String clave1 = x.nextLine();
                    System.out.println("Ingrese la clave del segundo aeropuerto");
                    String clave2 = x.nextLine();
                    if (aeropuertos.existeVertice(new Aeropuerto(clave1)) && aeropuertos.existeVertice(new Aeropuerto(clave2))) {
                        Lista temp2 = aeropuertos.listarEnAnchura();
                        int i=0;
                        Aeropuerto a = null,
                                b = null;
                        while (a == null || b == null){
                            Aeropuerto aerop = (Aeropuerto) temp2.recuperar(i);
                            String claveAerop="";
                            if (aerop!=null)
                                claveAerop = aerop.getClave();
                            if (a == null && claveAerop.equals(clave1))
                                a = aerop;
                            if (b == null && claveAerop.equals(clave2))
                                b = aerop;
                            i++;
                        }
                        System.out.println("Ingrese la cantidad máxima de vuelos");
                        int r = Integer.parseInt(x.nextLine());
                        System.out.println(recorreEn(a, b, r));
                    } else
                        System.out.println("Un aeropuerto no existe");
                }break;
                case ("18"): {
                    System.out.println("Ingrese la clave del primer aeropuerto");
                    String clave1 = x.nextLine();
                    System.out.println("Ingrese la clave del segundo aeropuerto");
                    String clave2 = x.nextLine();
                    if (aeropuertos.existeVertice(new Aeropuerto(clave1)) && aeropuertos.existeVertice(new Aeropuerto(clave2))) {
                        Lista temp2 = aeropuertos.listarEnAnchura();
                        int i=0;
                        Aeropuerto a = null,
                            b = null,
                            auxO = new Aeropuerto(clave1),
                            auxD = new Aeropuerto(clave2);
                        while (a == null || b == null){
                            Aeropuerto aerop = (Aeropuerto) temp2.recuperar(i);
                            if (a == null && aerop!=null &&aerop.equals(auxO))
                                a = aerop;
                            if (b == null && aerop!=null &&aerop.equals(auxD))
                                b = aerop;
                            i++;
                        }
                        System.out.println(caminoMenorTiempo(a, b));
                    } else
                        System.out.println("No existe un aeropuerto");
                }break;
                case ("19"): {
                    System.out.println(promocionClientes().toString());
                }break;
                case ("20"): {
                    System.out.println("Sistema:\n"+mostrarSistema());
                }break;
                default: {
                    if (opcion.equals("0"))
                        System.out.println("Operación finalizada.");
                    else
                        System.out.println("opción no válida.");
                }break;
            }
        } while(!(opcion.equals("0")));
    }
}
