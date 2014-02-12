import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


class Alumno{
    String nombre;
    int grado;
    char grupo;
    int calificacion;
    
    public Alumno(String n,int g,char gru,int cal){
        nombre=n;
        grado=g;
        grupo=gru;
        cal=calificacion;
    }
    
    public static void main(String[] args) {
	  //CONVERTIMOS DE JAVA A JSON
	  //crearemos los objetos de varios alumnos
	  ArrayList<Alumno> alumnos = new ArrayList();
	  Alumno alum1=new Alumno("Jose Perez",2,'A',10);
	  Alumno alum2=new Alumno("Jonathan Melgoza",3,'B',7);
	  Alumno alum3=new Alumno("Alexandra Ceballos",1,'A',8);
	  //agregamos los alumnos a una lista
	  alumnos.add(alum1);
	  alumnos.add(alum2);
	  alumnos.add(alum3);
	  Gson gson = new Gson();
	  //convertimos la lista de alumnos a formato JSON
	  String formatoJSON = gson.toJson(alumnos);
	  //imprimimos en consola el texto con formato JSON
	  System.out.println("Texto en Formato JSON de los alumnos agregados:\n"+formatoJSON);
	  
	  
	//CONVERTIMOS DE JSON A JAVA
	//Guardamos en una lista los alumnos leidos desde la cadena en formato JSON
	//primero necesitaremos tener el tipo de objeto donde guardaremos la informacion que sera una lista de alumnos
	//despues en gson.gromjson los dos parametros son: la cadena en formato JSON donde leeras la informacion
	//y el tipo de clase/objeto donde guardaras la informacion
	java.lang.reflect.Type tipoObjeto = new TypeToken<List <Alumno>>(){}.getType();
	ArrayList<Alumno> alumnos2=gson.fromJson(formatoJSON, tipoObjeto);
	System.out.println("\nJSON A JAVA");
	for(int i=0;i<alumnos2 .size();i++){
		Alumno al=alumnos2.get(i);
		System.out.println(al.nombre+":"+al.grado+":"+al.grupo+":"+al.calificacion);
	}
    }
}

