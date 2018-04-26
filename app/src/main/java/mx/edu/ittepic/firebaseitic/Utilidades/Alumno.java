package mx.edu.ittepic.firebaseitic.Utilidades;

/**
 * Created by Zulma on 17/04/2018.
 */

public class  Alumno {
        private String nombre,ncontrol;

        public Alumno() {
        }

        public Alumno(String nombre, String ncontrol) {
                this.nombre = nombre;
                this.ncontrol = ncontrol;
        }

        public String getNombre() {
                return nombre;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public String getNcontrol() {
                return ncontrol;
        }

        public void setNcontrol(String ncontrol) {
                this.ncontrol = ncontrol;
        }
}
