package mx.edu.ittepic.firebaseitic.Utilidades;

/**
 * Created by Zulma on 17/04/2018.
 */

public class  Alumno {
        String nombrealumno;

        public Alumno(String nombrealumno, String numerocontrol) {
                this.nombrealumno = nombrealumno;
                this.numerocontrol = numerocontrol;
        }
        public Alumno() {

        }
        public String getNombrealumno() {

                return nombrealumno;
        }

        public void setNombrealumno(String nombrealumno) {
                this.nombrealumno = nombrealumno;
        }

        public String getNumerocontrol() {
                return numerocontrol;
        }

        public void setNumerocontrol(String numerocontrol) {
                this.numerocontrol = numerocontrol;
        }

        String numerocontrol;


}
