package controller;

import model.Students;
import model.StudentsDTO;
import view.View;
/**
 * Clase encargada hacer las llamadas y controlar el flujo
 * @author Jhon Erick Santos
 * @version 1.0
 */
public class Controller {
    /**Atributos*/

    private View view;
    private StudentsDTO institute;

    /**Constructor*/
    public Controller(){
        view = new View();
        institute = new StudentsDTO();
        function();
    }

    /**
     * Método para desplegar menu y arranque del sistema
     */
    public void function(){
        int option = 0;

        String menu ="INSTITUTO LA FLORESTA" +"\n"+
                    "Seleccione tarea a realizar: " + "\n" +
                    "1. Ingresar Estudiante" + "\n" +
                    "2. Buscar Estudiante" + "\n" +
                    "3. Modificar Estudiante" + "\n" +
                    "4. Eliminar Estudiante" + "\n" +
                    "5. Ver directorio de Estudiantes" + "\n" +
                    "6. Salir" + "\n" +
                    "Opción:";

        do {
            option = view.readDataInt(menu);
            switch (option){
                case 1:
                    addStudent();
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    modifyStudents();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    showDirectory();
                    break;
                case 6:
                    view.showInformation("Hasta pronto");
                    break;
                default:
                    view.showInformation("Error: Opción no valida");
            }
        }while(option !=6);
    }

    //Metodos CRUD formulario de fuction
    /**
     * Método para leer la información necesaria para crear un estudiante
     * LLama a la función crear estudiante de la clase DAO
     */
    public void addStudent(){
        String n,a,fn,ci,cp,pr;
        long cel,fi;
        n=a=fn=ci=cp=pr = "";
        cel=fi= 0;
        view.showInformation("Ingresar estudiante");
        n = view.readDataString("Ingresar nombres:");
        a = view.readDataString("Ingresar apellidos:");
        fn = view.readDataString("Ingresar fecha de nacimiento (YYYY-MM-DD):");
        ci = view.readDataString("Ingresar correo institucional:");
        cp = view.readDataString("Ingresar correo personal:");
        cel = view.readDataLong("Ingresar número de celular:");
        fi = view.readDataLong("Ingresar número fijo:");
        pr = view.readDataString("Ingresar programa:");

        if (institute.getStudentDao().addStudents(n,a,fn,ci,cp,cel,fi,pr)){
            view.showInformation("Se agregó el estudiante\n");
        }else{
            view.showInformation("No ha sido posible agregar al estudiante");
        }
    }

    /**
     * Método para leer la información de un estudiante a buscar
     * Llama la función buscar estudiante de la clase DAO
     */
    public void searchStudent(){
        String ci = "";
        view.showInformation("Buscar estudiante");
        ci = view.readDataString("Ingresar correo institucional:");
        Students response = institute.getStudentDao().searchStudent(ci);
        if (response != null){
            view.showInformation("Información del estudiante");
            view.showInformation(response.toString());
        }else{
            view.showInformation("El estudiante no se encuentra registrado en el instituto");
        }
    }

    /**
     * Método para leer la información de un estudiante y actualizar
     * Llama la función buscar estudiante de la clase DAO
     */
    public void modifyStudents(){
        String n,a,fn,ci,cp,pr;
        long cel,fi;
        n=a=fn=ci=cp=pr = "";
        cel=fi= 0;
        view.showInformation("Modificar estudiante");
        ci = view.readDataString("Ingresar correo institucional:");
        Students response = institute.getStudentDao().searchStudent(ci);
        if (response != null){
            n = response.getName();
            a = response.getLastName();
            fn = response.getDataOfBirth();
            cp = view.readDataString("Ingresar correo personal:");
            cel = view.readDataLong("Ingresar número de celular:");
            fi = view.readDataLong("Ingresar número fijo:");
            pr = view.readDataString("Ingresar programa:");
            institute.getStudentDao().modifyStudent(n,a,fn,ci,cp,cel,fi,pr);
            view.showInformation("Se modificó el estudiante\n");
        }else{
            view.showInformation("El estudiante no se encuentra registrado en el instituto");
        }
    }

    /**
     * Método para leer la información de un estudiante y borrarlo
     * Llama la función eliminar estudiante de la clase DAO
     */
    public  void removeStudent(){
        String ci = "";
        view.showInformation("Eliminar estudiante");
        ci = view.readDataString("Ingresar correo institucional:");
        if(institute.getStudentDao().deleteStudent(ci)){
            view.showInformation("Se eliminó el estudiante\n");
        }else{
            view.showInformation("El estudiante no se encuentra registrado en el instituto");
        }
    }

    /**
     * Método para mostrar la información del directorio
     * Llama la función mostrar estudiante de la clase DAO
     */
    public void showDirectory(){
        view.showInformation("El directorio de los estudiantes\n");
        String n = institute.getStudentDao().showStudent();
        view.showInformation(n);
    }

}
